package ro.rsbideveloper.rsbi.test

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ro.rsbideveloper.rsbi.databinding.NetworkConnectivityActivityBinding

// <TODO> so, for the "offline-first" application, if there is a network connection available, it will make a request
    // to update the data it has already cached locally; it will make the request on the IO Dispatcher, but will also,
    // on a different Dispatcher, check the cache version and load that into the Ui; it will have an indicator that there
    // is an active network request being made; if the network request returns a response with new data / changes to old
    // data  / removal of old data (?)[I am not so sure about this actually; I might also want to instead delete nothing, and
    // also store all the different versions of articles / posts in the cache {basically all that the user has ever encountered /
    // received from the server}; these can be manually deleted though, instead; or, the whole cache could be cleaned from the app,
    // with a button tap, but do show the "Are you sure ?" alertDialog first]
// <TODO> have a Ui indicator for when the network connection goes out or comes back on (~ Youtube has, but styled differently);
    // also, have a fragment where the network traffic of the application is recorded and a report is made of how much network traffic
    // the app has generated (although the operating system itself also does this, but so what ? I want it directly in the app, easily accessible)
// <TODO> when requests are made, they need to be able to restart / continue (continuing is likely harder to do though, as it requires that both
    // the client and the server have a mechanism that supports for this, unless the request is actually a "batch", in which case
    // the client0side just has to keep track of things) the request automatically (!?), but have mechanisms to prevent "over-queueing"
    // the command / network requests buffer
// <TODO> if there are parameters that can quickly vary between the client and server (such as the number of products available on stock at
    // an online market), somehow mark those and have the Ui display warnings about the trustability / "up-to-dateness" / reliability
    // of the data when in offline mode
// <TODO>

class NetworkConnectivityMonitor : AppCompatActivity() {
    companion object {
        const val availableInternetConnection = "At least a network with Internet connection is available"
        const val unavailableInternetConnection = "There is no available network with Internet connection"
    }

    private lateinit var binding: NetworkConnectivityActivityBinding
    private lateinit var networkState: LiveData<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkState = NetworkConnectivityLiveData(this)


        // (XML) View binding variant
        networkState.observe(this, Observer {   // <TODO> so this variant of .observe() is for the View-based architecture design, whereas the .observeAsState is for Jetpack Compose
            if(it) {
                binding.NetworkConnectivityActivityRootTvState.text = "Detected at least an active network"
            } else {
                binding.NetworkConnectivityActivityRootTvState.text = "No active network detected"
            }
        })
        binding = NetworkConnectivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /// Compose variant
        setContent {
            // <TODO> nullable
            val isNetworkAvailable = networkState.observeAsState()  // <TODO> this is supposedly a Compose-specific thing; it wraps the Boolean value in a State type -> State<Boolean?>
                        // "it's how you observe LiveData<> inside a @Composable, essentially
            if (isNetworkAvailable.value != null) {
                ConnectivityMonitor(isNetworkAvailable.value!!)   // <TODO> setting the initial state, but it's too bad that it doesn't really have any "pre-compute await" mechanism (or maybe it does, if you do it "strangely")
            } else {
                ConnectivityMonitor(false)
            }

            // <TODO> but apparently state can have an "initial value" defined here, more succintly
            val isNetworkAvailable2 = networkState.observeAsState(initial = false).value    // <TODO> this way it becomes non-null (!)
            ConnectivityMonitor(isNetworkAvailable2)
        }
    }
}

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean
) {
//    if(!isNetworkAvailable) {
//        Text(text = NetworkConnectivityMonitor.unavailableInternetConnection)
//    } else {
//        Text(text = NetworkConnectivityMonitor.availableInternetConnection)
//    }
}