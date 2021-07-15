package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.eventspage.*
import kotlinx.android.synthetic.main.publicpage.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.PublicpageBinding

class Publicpage : Fragment(R.layout.publicpage) {
    private var _binding: PublicpageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PublicpageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Publicpage_btn.setOnClickListener {
            findNavController().navigate(PublicpageDirections
                .actionPublicpageToHomepageNav2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}