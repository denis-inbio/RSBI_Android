package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.home_page.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.HomePageBinding

class Home_page : Fragment(R.layout.home_page) {

    private var _binding: HomePageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Home_page_btn.setOnClickListener {
            findNavController().navigate(Home_pageDirections
                .actionHomepageNavToEventspage2())
        }

//        Homepage_bottom_nav.setupWithNavController()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}