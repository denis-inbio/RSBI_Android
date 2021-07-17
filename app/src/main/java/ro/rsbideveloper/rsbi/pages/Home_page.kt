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

// <TODO> the Home_page needs to present the latest news (and important ones), or some sort of
    // frequently updated information; but, it will also need to contain the search mechanism
    // for the whole database; when the search's AutocompleteEditTextView is focused, the layout will
    // change: it will contain a drop-down list of suggestions (which would include the recent search
    // history) [the suggestions will be "partially matched" with what is already written in the search
    // field] (the suggestions could also include existing events / posts / "thing" in the database)

// <TODO> how to implement (and design) the filtering options in that "second layout" of the Home_page ? (when the
    // AutocompleteEditTextView is focused)

// <TODO>

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