package ro.rsbideveloper.rsbi.test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ServiceCom : Service() {

    companion object {
        private lateinit var serviceSINGLETON: ServiceCom
        private lateinit var threadSINGLETON: Thread
        // <TODO> (!?) need persistent state of different kinds -> across application instances, across starts and stops
        var isRunning = false

        fun stopService() { // <TODO> I guess I cannot override onStopService, but I can add things / compose to id
                                // (as "preceding sequentiality")
            Log.d("SERVICECOM", "Stopping service")
            isRunning = false

//            threadSINGLETON.interrupt()
//            threadSINGLETON.stop()
            serviceSINGLETON.stopSelf()
        }
    }

    init {
        Log.d("SERVICECOM", "Initiated service")    // <TODO> stopping a service seems to destroy it, because on the next
                                                                // start it again calls this; on the other hand, the first call to init
                                                                // is not triggered by the user's Ui button (?!)
        serviceSINGLETON = this
        threadSINGLETON = Thread {
            while(true) {
                Log.d("SERVICECOM", "Service alive")
                Thread.sleep(1000)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        // <TODO> used when there are / can be multiple clients that can interact with this service;
            // for this application, only one client will ever interact with the service
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // <TODO> in which case is the intent null ? how can a service be started by a null intent ?
            // next though, it is easy to see what data?.let{} is needed for, because not all calls to the
            // service will also pass on any data
        val data = intent?.getStringExtra("extra-string")
        data?.let {
            Log.d("SERVICECOM", "The data transmitted is: $data")
        }


        isRunning = true

        if(!threadSINGLETON.isAlive) {
            threadSINGLETON?.start()   // <TODO> so, this needs to be a singleton; next, this should be destroyed together with the service, but how do
            // I override ~ onStopService() ?
        }

//        return super.onStartCommand(intent, flags, startId) // ??
//        return START_NOT_STICKY // not recreated
        return START_STICKY // recreated when possible
//        return START_REDELIVER_INTENT   // something about getting the last intent's state (and that in the first case it would be null)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SERVICECOM", "Service is being destroyed")   // <TODO> apparently killing the app is more like a
                                                                        // forceful End Process analog; it doesn't end up
                                                                        // calling onDestroy() here
    }
}