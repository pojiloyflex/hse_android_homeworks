package io.github.hse.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun getLocaleChangedFlow(context: Context) = callbackFlow {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action == Intent.ACTION_LOCALE_CHANGED) {
                val locale = context?.resources?.configuration?.locales?.get(0)
                if (locale != null) {
                    trySend(locale)
                }
            }
        }
    }
    context.registerReceiver(receiver, IntentFilter(Intent.ACTION_LOCALE_CHANGED))

    awaitClose {
        context.unregisterReceiver(receiver)
    }
}