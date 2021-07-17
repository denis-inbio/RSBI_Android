package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.StudyPageBinding

// <TODO> this seems like something that will need to be kept up-to-date, so a server request will be needed;
    // also, new entries should be addable by users, which are posted for review to admins, who will
    // validate the data

// <TODO>

class Study_page : Fragment(R.layout.study_page) {
    private var _binding: StudyPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StudyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}