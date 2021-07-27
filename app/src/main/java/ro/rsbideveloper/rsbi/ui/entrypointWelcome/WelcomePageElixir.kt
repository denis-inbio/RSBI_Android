package ro.rsbideveloper.rsbi.ui.entrypointWelcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import ro.rsbideveloper.rsbi.databinding.WelcomePageElixirBinding

class WelcomePageElixir : Fragment() {
    private lateinit var binding: WelcomePageElixirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageElixirBinding.inflate(layoutInflater)
    }
}