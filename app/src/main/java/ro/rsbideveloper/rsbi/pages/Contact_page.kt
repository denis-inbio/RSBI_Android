package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.contact_page.*
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.ContactPageBinding

// <TODO> this page seems to be mainly about writing an email actually, without really requiring one
    // to use their email (there is no verification that the input email is actually "yours")
    // the destination / feedback email is contact@rsbi.ro
// <TODO> there is apparently also a "I am human" / "reCaptcha" check

class Contact_page : Fragment(R.layout.contact_page) {
    private var _binding: ContactPageBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContactPageBinding.inflate(inflater, container, false)
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