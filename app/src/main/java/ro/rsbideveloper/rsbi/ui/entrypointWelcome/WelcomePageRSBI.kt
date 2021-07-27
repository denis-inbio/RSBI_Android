package ro.rsbideveloper.rsbi.ui.entrypointWelcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import ro.rsbideveloper.rsbi.databinding.WelcomePageRsbiBinding

class WelcomePageRSBI : Fragment() {
    private lateinit var binding: WelcomePageRsbiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageRsbiBinding.inflate(layoutInflater)
    }
}