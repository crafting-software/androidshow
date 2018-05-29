package com.craftingsoftware.androidshow.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    private var state = 0
    private var number = ""
    private var savedNumber = ""

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_NEW_OUTGOING_CALL) {
            savedNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)

        } else {
            val stateStr = intent.extras.getString(TelephonyManager.EXTRA_STATE)
            number = intent.extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)

            when (stateStr) {
                TelephonyManager.EXTRA_STATE_IDLE -> state = TelephonyManager.CALL_STATE_IDLE
                TelephonyManager.EXTRA_STATE_RINGING -> state = TelephonyManager.CALL_STATE_RINGING
            }
        }

        onCallStateChanged(context, state, number)
    }

    private fun onCallStateChanged(context: Context, state: Int, number: String) {

        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                savedNumber = number
                Toast.makeText(context, "Incoming Call from $savedNumber", Toast.LENGTH_LONG).show()
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                savedNumber = number
                Toast.makeText(context, "Missed Call from $savedNumber", Toast.LENGTH_LONG).show()
            }
        }
    }
}