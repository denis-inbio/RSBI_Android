package ro.rsbideveloper.rsbi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ro.rsbideveloper.rsbi.databinding.SplashcreenBinding
import ro.rsbideveloper.rsbi.test.WebViewRSBI

class Splashscreen : Fragment(R.layout.splashcreen) {
    private var _binding: SplashcreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashcreenBinding.inflate(inflater, container, false)

        binding.SplashscreenBtn.setOnClickListener {
            findNavController().navigate(SplashscreenDirections
                .actionFirstFragmentToSecondFragment())
        }

        binding.SplashscreenBtnTestWebView.setOnClickListener {
            startActivity(Intent(context, WebViewRSBI::class.java))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}