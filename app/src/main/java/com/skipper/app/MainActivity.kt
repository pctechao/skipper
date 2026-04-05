package com.skipper.app

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private var isEnabledState = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isEnabled by isEnabledState

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF0F0F0F))
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))

                // Header
                Text("🐺", fontSize = 72.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Skipper", fontSize = 36.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                // Status
                Text(
                    if (isEnabled) "✅ Service is ON — Skipping ads!" else "❌ Service is OFF",
                    color = if (isEnabled) Color(0xFF00CC66) else Color(0xFFFF5555),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Enable button
                Button(
                    onClick = {
                        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                        startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Enable Skipper", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(48.dp))
                HorizontalDivider(color = Color(0xFF333333))
                Spacer(modifier = Modifier.height(24.dp))

                // Banking app warning
                Text("🏦 Banking Apps", fontSize = 14.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Some banking and payment apps might complain about Skipper service and may show a security warning or refuse to open.",
                    fontSize = 18.sp,
                    color = Color(0xFF888888),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "⚠️ Before opening banking apps:\n1. Tap \"Enable Skipper\" above\n2. Find Skipper in the list\n3. Toggle it OFF\n4. Re-enable it when done",
                    fontSize = 18.sp,
                    color = Color(0xFFFFAA00),
                    textAlign = TextAlign.Left,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(color = Color(0xFF333333))
                Spacer(modifier = Modifier.height(24.dp))

                // App info — replace with your real details
                Text("Version 1.1", fontSize = 12.sp, color = Color(0xFF666666))
                Text("Ahmad Omar", fontSize = 12.sp, color = Color(0xFF666666))
                Text("eng.pctec@gmail.com", fontSize = 12.sp, color = Color(0xFF666666))

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isEnabledState.value = isAccessibilityServiceEnabled()
    }

    private fun isAccessibilityServiceEnabled(): Boolean {
        val enabledServices = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false
        return enabledServices.contains("com.skipper.app.SkipperService")
    }
}