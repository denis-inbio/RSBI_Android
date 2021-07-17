package ro.rsbideveloper.rsbi.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ro.rsbideveloper.rsbi.MVVM.event.Event
import ro.rsbideveloper.rsbi.MVVM.event.EventViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.WriteEventDialogBinding
import ro.rsbideveloper.rsbi.recycler_adapters.EventsRecyclerViewAdapter

// <TODO> in large measure, the creation class's layout is similar to the edit class's layout, so
// can something be done about that ? I mean, if it's about the text, it can be dynamically
// modified in the logic; if it's about the general shape, that is mostly (/ completly ?) shared
// if it's about the setOnClickListener{}'s, then those are set in the class's logic anyway

// <TODO> (*?) but what would be the proper "wording" to use: "add", "update", "edit", "write" (!), "create",
// "input", "modify", ..?

// <TODO> the WriteEvent class would have to take a nullable Event as input (through safeargs), such that when
// the parameter is null then it will create a new one, and when it's not null it will load its data
// into the Ui and allow for its modification

// <TODO>

class WriteEvent_form : Fragment(R.layout.write_event_dialog) {
    private var _binding: WriteEventDialogBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: EventViewModel
    private var event: Event? = null

    private val args by navArgs<WriteEvent_formArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WriteEventDialogBinding.inflate(inflater, container, false)
        _binding?.WriteEventBtn?.setOnClickListener {
            validateAndInsertData()
        }

        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        Toast.makeText(context, "Received id: ${args.eventId}", Toast.LENGTH_SHORT).show()
        val eventId = args.eventId as Int

        viewModel.dataSelectById?.observe(viewLifecycleOwner, Observer {
            event = it
        }


            (_binding?.EventsPageRecyclerView?.adapter as EventsRecyclerViewAdapter).setData(it)
        })
        fun setData(events: List<Event>) {
            this.dataList = events


        Toast.makeText(context, "Received Event: $event", Toast.LENGTH_SHORT).show()

        if(event != null) {
            // <TODO> Update / Edit mode
            _binding?.WriteEventEt1?.setText(event?.id.toString())
            _binding?.WriteEventEt2?.setText(event?.published_by)
            _binding?.WriteEventEt3?.setText(event?.image_URL)
            _binding?.WriteEventBtn?.setText("Update")

        } else {
            // <TODO> Create Mode
            _binding?.WriteEventBtn?.setText("Create")
        }

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
            if(event != null) {
                updateData(it)
                Log.d("DebugData", "Updated data to database")
            } else {
                insertData(it)
                Log.d("DebugData", "Wrote data to database")
            }
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

            Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show()

            findNavController().navigate(WriteEvent_formDirections
                .actionWriteEventPageNavToEventsPageNav())

        }
    }

    private fun validateData(): Event? {
        val param1 = _binding?.WriteEventEt1?.text.toString()
        val param2 = _binding?.WriteEventEt1?.text.toString()
        val param3 = _binding?.WriteEventEt1?.text.toString()

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

    private fun updateData(event: Event) {
        // the autogenerate for the primary key messes things up here
        val updatedEvent = Event(event.id, _binding?.WriteEventEt2?.text.toString(), _binding?.WriteEventEt2?.text.toString(),
                "", "", "", "", "","","","",
                    "","", "","")

        Toast.makeText(context, "Initial event: $event", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, "Updated event: $updatedEvent", Toast.LENGTH_SHORT).show()

        viewModel.updateEvent(updatedEvent)    // (*?) how does it do this (identify the correct
                                            // entry in the database to update) ? based on the id ?
                                        // (*?) is the update a transaction / @Volatile / locked / cache
                                            // coherent across threads ?
    }
}