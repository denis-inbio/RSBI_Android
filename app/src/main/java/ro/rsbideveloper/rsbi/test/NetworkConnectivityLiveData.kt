package ro.rsbideveloper.rsbi.test

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

class NetworkConnectivityLiveData(context: Context) : LiveData<Boolean>() {
    private val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager // <TODO> still, it's a strange import, this CONNECTIVITY_SERVICE
    // <TODO> how to get / what are the differences to ConnectivityManagerCompat (?!)
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val validNetworks: MutableSet<Network> = HashSet()

    private fun checkValidNetworks() {
        // <TODO> it seems to be a way to set the value of the LiveData ? why "post" though ?
        postValue(validNetworks.size > 0)   // <TODO> what is $postValue about ? it seems to be from LiveData<>'s scope
    }

    // <TODO> apparently onActive() means that there are observers
    override fun onActive() {   // <TODO> what does onActive() signify ? is this when the LiveData<> gets created / initialized ?
                                // apparently, this is an RAII analog (or the ctor - dtor cycle), so it can be used to ensure behaviour
                                    // at creation and on destruction
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET) // <TODO> this NET_CAPABILITY_INTERNET is also a strange import
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        super.onActive()    // <TODO> what does this do ? the speaker had this deleted
    }

    // <TODO> and onInactive() means that there are no observers; but I assume that this not mean that the LiveData<> itself gets destroyed though (!?)
    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
        super.onInactive()
    }

    // <TODO> essentially yet another two callbacks, triggered by a conditional each
    private fun createNetworkCallback() = object: ConnectivityManager.NetworkCallback() {   // <TODO> interesting way to return an object with some of its methods overridden / defined
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val isInternet = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
            Log.d("NETWORKSTATE", "onAvailable(): ${network.toString()} has Internet state: $isInternet")

            if(isInternet == true) {
                validNetworks.add(network)
            }

            checkValidNetworks()    // <TODO> I guess this is just for the case where it goes from no available network to at least one available network
        }

        override fun onLost(network: Network) {
            super.onLost(network)

            Log.d("NETWORKSTATE", "onLost(): ${network.toString()} has Internet unavailable")

            validNetworks.remove(network)
            checkValidNetworks()
        }
    }
}