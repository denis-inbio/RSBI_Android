package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.eventspage.*
import kotlinx.android.synthetic.main.splashcreen.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.SplashscreenDirections
import ro.rsbideveloper.rsbi.databinding.EventspageBinding

class Eventspage : Fragment(R.layout.eventspage) {
    private var _binding: EventspageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EventspageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Eventspage_btn.setOnClickListener {
            findNavController().navigate(EventspageDirections
                .actionEventspageToWorkpage2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}