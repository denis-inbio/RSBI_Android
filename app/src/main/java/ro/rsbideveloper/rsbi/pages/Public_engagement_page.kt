package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.public_engagement_page.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.PublicEngagementPageBinding

class Public_engagement_page : Fragment(R.layout.public_engagement_page) {
    private var _binding: PublicEngagementPageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PublicEngagementPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Public_page_btn.setOnClickListener {
            findNavController().navigate(Public_engagement_pageDirections
                .actionPublicpageToHomepageNav2())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}