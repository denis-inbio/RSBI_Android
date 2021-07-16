package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.EssentialSkillsPageBinding
import kotlinx.android.synthetic.main.essential_skills_page.*
import kotlinx.android.synthetic.main.work_page.*

// <TODO> this page is static, it gets stored in as a <string-array> in an xml file, then at run-time
    // it will be read and turned into items for a RecyclerView
// <TODO>

class Essential_skills_page : Fragment(R.layout.essential_skills_page) {
    private var _binding: EssentialSkillsPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EssentialSkillsPageBinding.inflate(inflater, container, false)
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