package ro.rsbideveloper.rsbi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.splashcreen.*
import ro.rsbideveloper.rsbi.databinding.SplashcreenBinding

class Splashscreen : Fragment(R.layout.splashcreen) {

    private var _binding: SplashcreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SplashcreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Splashscreen_btn.setOnClickListener {
            findNavController().navigate(SplashscreenDirections
                .actionFirstFragmentToSecondFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}