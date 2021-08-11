package ro.rsbideveloper.rsbi.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import ro.rsbideveloper.rsbi.databinding.CatFactActivityBinding

class CatFactActivity : AppCompatActivity() {
    private lateinit var binding: CatFactActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CatFactActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.CatFactActivityRootProgressRequestFact.visibility = View.GONE
        binding.CatFactActivityRootBtnRequestFact.setOnClickListener {
            makeApiRequest()
        }
    }

    fun makeApiRequest() {
        binding.CatFactActivityRootProgressRequestFact.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(CatFactInterface.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
//            val call = api.getRandomFact()  // <TODO> these are useful
//            call.timeout()
//            call.cancel()

            try {
                val response = api.getRandomFact()
                    .awaitResponse()  // or one can just await, but will this actually re-schedule / use interrupts, or just periodically check for the state ?
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        withContext(Dispatchers.Main) {
                            binding.CatFactActivityRootTvFact.text = it.text
                        }
                    }
                } else {
                    Log.d("CATFACT", "Request has failed: ${response.errorBody()}")
                }

            } catch(e: Exception) {
                Log.d("CATFACT", "Failed to make request; maybe there's no network connection ?")
                withContext(Dispatchers.Main) {
                    Toast.makeText(baseContext, "Failed to make request; maybe there's no network connection ?", Toast.LENGTH_SHORT).show()
                }
            }

            // <TODO> "persist the request" until network connection is restored, if it is restored (?!)

            withContext(Dispatchers.Main) {
                binding.CatFactActivityRootProgressRequestFact.visibility = View.GONE
            }
        }
    }
}