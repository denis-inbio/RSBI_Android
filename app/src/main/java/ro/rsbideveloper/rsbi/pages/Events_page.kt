package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.events_page.*
import kotlinx.android.synthetic.main.splashcreen.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.EventsPageBinding

// <TODO> all of the posts will need to have a generic adapter, a generic data type, a generic style (including
    // that "Read more..." functionality that gets displayed in the recycler view), ..?

class Events_page : Fragment(R.layout.events_page) {
    private var _binding: EventsPageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EventsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Events_page_btn.setOnClickListener {
            findNavController().navigate(Events_pageDirections
                .actionEventspageToWorkpage2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}