package com.skipper.app

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class SkipperService : AccessibilityService() {

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED or
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        info.packageNames = arrayOf("com.google.android.youtube")
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
        info.notificationTimeout = 500
        serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val root = rootInActiveWindow ?: return
        findAndClickSkip(root)
    }

    private fun findAndClickSkip(node: AccessibilityNodeInfo) {
        val skipTexts = listOf("skip", "skip ad", "skip ads")
        for (text in skipTexts) {
            val nodes = node.findAccessibilityNodeInfosByText(text)
            for (skipNode in nodes) {
                if (skipNode.isClickable) {
                    skipNode.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return
                }
                val parent = skipNode.parent
                if (parent != null && parent.isClickable) {
                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return
                }
            }
        }
    }

    override fun onInterrupt() {}
}