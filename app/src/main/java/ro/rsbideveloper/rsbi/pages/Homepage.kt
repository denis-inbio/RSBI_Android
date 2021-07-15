package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.eventspage.*
import kotlinx.android.synthetic.main.homepage.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.HomepageBinding

class Homepage : Fragment(R.layout.homepage) {

    private var _binding: HomepageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Homepage_btn.setOnClickListener {
            findNavController().navigate(HomepageDirections
                .actionHomepageNavToEventspage2())
        }

//        Homepage_bottom_nav.setupWithNavController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}