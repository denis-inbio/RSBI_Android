package ro.rsbideveloper.rsbi.ui.entrypointWelcome

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ro.rsbideveloper.rsbi.databinding.WelcomeBinding

class Welcome : AppCompatActivity() {
    private lateinit var binding: WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = WelcomeBinding.inflate(layoutInflater)

        // viewmodel, etc.
    }
}