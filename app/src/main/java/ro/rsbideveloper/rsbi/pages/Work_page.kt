package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.work_page.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.WorkPageBinding

class Work_page : Fragment(R.layout.work_page) {
    private var _binding: WorkPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WorkPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Work_page_btn.setOnClickListener {
            findNavController().navigate(Work_pageDirections
                .actionWorkpageToPublicpage())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}