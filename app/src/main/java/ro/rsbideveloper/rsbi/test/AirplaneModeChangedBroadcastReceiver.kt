package ro.rsbideveloper.rsbi.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AirplaneModeChangedBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("BROADCAST", "Airplane mode changed")
        intent?.extras?.toString()?.let {
            Log.d("BROADCAST", it)
            val state = intent?.getBooleanExtra("state", false) ?: false    // <TODO> in the "? <case> : false", does
                                            // case implicitly return the result of the expression ?
            Log.d("BROADCAST", "Airplane: $state")
        }


    }

}