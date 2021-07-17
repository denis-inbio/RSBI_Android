package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.PublicEngagementPageBinding

class Public_engagement_page : Fragment(R.layout.public_engagement_page) {
    private var _binding: PublicEngagementPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PublicEngagementPageBinding.inflate(inflater, container, false)

        binding.PublicPageBtn.setOnClickListener {
            findNavController().navigate(Public_engagement_pageDirections
                .actionPublicpageToHomepageNav2())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}