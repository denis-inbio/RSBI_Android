package ro.rsbideveloper.rsbi.ui.entrypointWelcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import ro.rsbideveloper.rsbi.databinding.WelcomePageCommunityBinding

class WelcomePageCommunity : Fragment() {
    private lateinit var binding: WelcomePageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageCommunityBinding.inflate(layoutInflater)
    }
}