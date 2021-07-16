package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.about_us_page.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.AboutUsPageBinding

// this will contain both "Steering Committee" and "Member List"
// <TODO> now, apparently the member list also has geolocation data, so it can
    // be rendered in Google Maps
class About_us_page : Fragment(R.layout.about_us_page) {
    private var _binding: AboutUsPageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutUsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}