package com.skipper.app

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private var isEnabledState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isEnabled by isEnabledState

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0F0F0F)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⛵", fontSize = 72.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Skipper", fontSize = 36.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        if (isEnabled) "✅ Service is ON — Skipping ads!" else "❌ Service is OFF",
                        color = if (isEnabled) Color(0xFF00CC66) else Color(0xFFFF5555)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                            startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Enable Skipper", fontSize = 16.sp)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val serviceName = "$packageName/.SkipperService"
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: ""
        android.util.Log.d("Skipper", "Looking for: $serviceName")
        android.util.Log.d("Skipper", "Enabled services: $enabledServices")
        isEnabledState.value = isAccessibilityServiceEnabled()
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val serviceName = "$packageName/.SkipperService"
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        return enabledServices.contains("com.skipper.app.SkipperService")
    }
}