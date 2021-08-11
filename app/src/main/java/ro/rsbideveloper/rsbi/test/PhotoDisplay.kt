package ro.rsbideveloper.rsbi.test

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateColorAsState
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.rsbideveloper.rsbi.databinding.PhotoDisplayBinding

class PhotoDisplay : AppCompatActivity() {
    private lateinit var binding: PhotoDisplayBinding
    private val MAXIMUMNUMBEROFTRIES: Int = 5
    private val MAXIMUMFILESIZEBYTES: Int = 4 * 1024 * 1024 // * 0 // <tested> it works well

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PhotoDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)  // <TODO> interesting, might use this, might not
            // (*?) but what's up with the AppCompatDelegate thing ?

        initializeBackgroundAnimation()

        binding.PhotoDisplayRootFloatBtnRefresh.setOnClickListener {
            it.animate().apply {
                rotationBy(360f)
                duration = 1000 // [ms]
            }.start()

             requestAPIRandomDog()
        }
    }

    private fun initializeBackgroundAnimation() {
        val animation: AnimationDrawable = binding.PhotoDisplayRoot.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }

    private fun requestAPIRandomDog() {
        val instantiatedApi = Retrofit.Builder()
            .baseUrl(PhotoRequestInterface.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoRequestInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                var count = 0
                while(count < MAXIMUMNUMBEROFTRIES) {
                    val response = instantiatedApi.getRandomDog()
                    Log.d("PHOTODISPLAY", "Received JSON:\nfileSizeBytes: ${response.fileSizeBytes} Bytes\nUrl:${response.url}")

                    if(response.fileSizeBytes > MAXIMUMFILESIZEBYTES) {
                        count++
                    } else {
                        withContext(Dispatchers.Main) { // <TODO> why not IO again ? Apparently it throws an error (!), probably due to context and where the image gets saved ? well, this is a downside for Glide then
                                                            // but does Glide tolerate when the internet connection interrupts > or when it changes from mobile data to wi-fi, back and forth ?
                            Glide.with(applicationContext)
                                .load(response.url)
                                .into(binding.PhotoDisplayRootIvImage)
                        }

                        break;
                    }

                    if(count == MAXIMUMNUMBEROFTRIES) {
                        Log.d("PHOTODISPLAY", "None of the random images was smaller than the maximum allowed size")
                    }
                }
            } catch (e: Exception) {
                Log.d("PHOTODISPLAY", "Exception when making request: ${e.message}")
            }
        }
    }
}