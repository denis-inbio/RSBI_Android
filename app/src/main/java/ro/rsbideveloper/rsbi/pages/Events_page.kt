package ro.rsbideveloper.rsbi.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ro.rsbideveloper.rsbi.MVVM.event.EventViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.EventsPageBinding
import ro.rsbideveloper.rsbi.recycler_adapters.EventsRecyclerViewAdapter

// <TODO> all of the posts will need to have a generic adapter, a generic data type, a generic style (including
    // that "Read more..." functionality that gets displayed in the recycler view), ..?

// <TODO> interface -> onLongClick to show a list of options (edit Event, remove Event, etc.)
    // now, editing an event is possible for anybody, locally, but only those with authorization
    // can also sync that with the server's database; also, due to this, there needs to be an option for synching
    // an event and then for synching all events ("list operations")

// <TODO> further define what the "list operations" should be and what the "item operations" should be; next
    // would be the "CRUD operations" (of which adding an event is a "main action", which is why the floating action button)

// <TODO>

class Events_page : Fragment(R.layout.events_page) {
    private var _binding: EventsPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventsPageBinding.inflate(inflater, container, false)

        binding?.EventsPageFloatbtnAddEvent?.setOnClickListener {
            findNavController().navigate(Events_pageDirections
                .actionEventsPageNavToWriteEventPageNav(-1))
        }

        binding?.EventsPageBtn?.setOnClickListener {
            findNavController().navigate(Events_pageDirections
                .actionEventspageToWorkpage2())
        }

        binding?.EventsPageRecyclerView?.adapter = EventsRecyclerViewAdapter()
        binding?.EventsPageRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            // (*?) apparently this "observe" callback needs to also initiate the list; in fact, it needs
                // to cover all possible cases of "synchronicity", to ensure "integrity" [not just "data change triggers"]
            (binding?.EventsPageRecyclerView?.adapter as EventsRecyclerViewAdapter).setData(it)
        })

        // (!!) let's try generating data asynchronously, to test the effectiveness of the ViewModel
//        runBlocking {
//            launch {
//                Log.d("DebugCoroutine", "runBlocking launch - thread ${Thread.currentThread()}")
//            }
//            launch(Dispatchers.Unconfined) {  // (*?) "it inherits from the last suspend function"
//                delay(1000)   // for example this delay() function will supposedly change the thread, so that means
//                                  // that this scope changes the context / dispatcher / thread depending on what it executes
//                                  // as the latest function (and "where that function comes from"); but, so where does the delay()
//                                  // function come from ?
//                              // "inherits it from the last suspend function"
//                              // probably that long chains of operations across multiple threads are usefully done
//                                  // using this Dispatcher (such as involving IO, then Ui, then Main, then Default, etc.)
//                Log.d("DebugCoroutine", "runBlocking launch Dispatchers.Unconfined - thread ${Thread.currentThread()}")
//            }
//            launch(Dispatchers.Default) { // (*?) this is supposedly the same as GlobalScope.launch { ; },
                                                // so it's a / the GlobalScope; useful for "CPU intensive"
//                Log.d("DebugCoroutine", "runBlocking launch Dispatchers.Default - thread ${Thread.currentThread()}")
//            }
//            launch(Dispatchers.IO) {
//                Log.d("DebugCoroutine", "runBlocking launch Dispatchers.IO - thread ${Thread.currentThread()}")
//            }
//            launch(Dispatchers.Main) {
//                Log.d("DebugCoroutine", "runBlocking launch Dispatchers.Main - thread ${Thread.currentThread()}")
//            }
//            launch(newSingleThreadContext("CustomThread")) {
//                Log.d("DebugCoroutine", "runBlocking launch newSingleThreadContext(`CustomThread`) - thread ${Thread.currentThread()}")
//            }
//        }

        // (*?) apparently a runBlocking{} following another runBlocking{} does not act well ? the app got stuck permanently...
            // on the other hand, this is not the asynchronism that I needed
//        runBlocking { // (*?) runBlocking{} is supposedly "being launched from the main thread"
//            launch(Dispatchers.IO) {
//                delay(1000)
//
//                val event = Event(0, "delayed generated", "delayed", "generated","","", "", "",
//                    "","","", "", "", "", "")
//
//                viewModel.addEvent(event)   // (*?) well, the ViewModel could very well integrate multiple @Entity from across
//                                                // multiple @Database which each use potentially different @Dao
//            }
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}