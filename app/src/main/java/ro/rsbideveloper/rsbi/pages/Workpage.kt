package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.eventspage.*
import kotlinx.android.synthetic.main.workpage.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.WorkpageBinding

class Workpage : Fragment(R.layout.workpage) {
    private var _binding: WorkpageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = WorkpageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Workpage_btn.setOnClickListener {
            findNavController().navigate(WorkpageDirections
                .actionWorkpageToPublicpage())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}