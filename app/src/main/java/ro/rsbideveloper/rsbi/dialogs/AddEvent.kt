package ro.rsbideveloper.rsbi.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.add_event.*
import ro.rsbideveloper.rsbi.MVVM.Event
import ro.rsbideveloper.rsbi.MVVM.EventViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.AddEventBinding

class AddEvent : Fragment(R.layout.add_event) {
    private var _binding: AddEventBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddEventBinding.inflate(inflater, container, false)
        _binding?.AddEventBtn?.setOnClickListener {
            validateAndInsertData()
        }

        this.defaultViewModelProviderFactory
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
//        ).get(
//            EventViewModel::class.java
//        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun validateAndInsertData() {
        val data = validateData()
        data?.let {
            insertData(it)

            // <TODO> how to set an observer ? who sets it, the scope that wants / requests access to the
            //  "information (callback-updating) stream" or the "owner of the information" ?
//            viewModel.obser


            // <TODO> how to get data out of a LiveData<> ? (especially if it is private)
//            val events = viewModel.getAllEvents()
//            if(events != null) {
//                Log.d("DebugData", events.toString())
//            } else {
//                Log.d("DebugData", "events is null")
//            }
            Log.d("DebugData", "Wrote data to database")

            findNavController().navigate(AddEventDirections
                .actionAddEventToEventsPageNav())

        }
    }

    private fun validateData(): Event? {
        val param1 = _binding?.AddEventEt1?.text.toString()
        val param2 = _binding?.AddEventEt1?.text.toString()
        val param3 = _binding?.AddEventEt1?.text.toString()

        if(param1.isNotEmpty() &&
            param2.isNotEmpty() &&
            param3.isNotEmpty()
        ) {
            Toast.makeText(context, "Good input", Toast.LENGTH_SHORT).show()
        }

        val event = Event(0, param1, param2, param3,"","", "", "",
            "","","", "", "", "", "")

        return event
    }

    private fun insertData(event: Event) {
        viewModel.addEvent(event)
        Log.d("DebugData", "Inside insert")
    }
}