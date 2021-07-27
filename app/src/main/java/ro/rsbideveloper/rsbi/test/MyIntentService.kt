package ro.rsbideveloper.rsbi.test

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        private lateinit var SINGLETON: MyIntentService
        var isRunning = false

        fun stopService() {
            Log.d("INTENTSERVICE", "Stopping service")
            isRunning = false
            SINGLETON.stopSelf()
        }
    }

    init {
        SINGLETON = this
    }

    override fun onHandleIntent(intent: Intent?) {
        try {
            isRunning = true
            while(isRunning) {
                Log.d("INTENTSERVICE", "Service is running")
                Thread.sleep(1000)  // <TODO> apparently delay() is for coroutines and has to do with scheduling, whereas
                                            // sleep() is for threads (?!)
            }
        } catch(e: Exception) {
            Log.d("INTENTSERVICE", "Exception: ${e.message}")
            Thread.currentThread().interrupt()  // <TODO> ??
        }
    }
}