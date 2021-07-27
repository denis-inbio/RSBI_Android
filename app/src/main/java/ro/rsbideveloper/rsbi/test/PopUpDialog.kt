//package ro.rsbideveloper.rsbi.test
//
//import android.app.DatePickerDialog
//import android.app.TimePickerDialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.DatePicker
//import android.widget.TimePicker
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import kotlinx.coroutines.runBlocking
//import ro.rsbideveloper.rsbi.R
//import ro.rsbideveloper.rsbi.databinding.PopUpDialogBinding
//import ro.rsbideveloper.rsbi.pages.Work_pageDirections
//import java.util.*
//
//    // <TODO> avoid using inheritance (doesn't it just make type checking harder ? or the design becomes
//        // "uglier" or "uselessly complex" ?); instead, just define some inner function(s) for the listener(s)
//class PopUpDialog : Fragment(R.layout.pop_up_dialog), DatePickerDialog.OnDateSetListener,
//    TimePickerDialog.OnTimeSetListener {
//    private var _binding: PopUpDialogBinding? = null
//    private val binding get() = _binding!!
//
//    // replace with a ViewModel
//    var year = 0
//    var month = 0
//    var day = 0
//    var hour = 0
//    var minute = 0
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = PopUpDialogBinding.inflate(inflater, container, false)
//
//        // (*?) programmatically
//        binding.PopUpDialogBtn.setOnClickListener {
//            Calendar.getInstance().also {
//                DatePickerDialog(requireContext(), this,
//                    it.get(Calendar.YEAR),
//                    it.get(Calendar.MONTH),
//                    it.get(Calendar.DAY_OF_MONTH)).show()
//                // (*?) this is "bad design"; I don't want to compose the TimePicker in the
//                // callback for the DatePicker, because it's no longer "in-place visible",
//                // and also, it affects the modularity of the callbacks [the onTimeSet() now
//                // depends on the onDateSet()]
//            }
//        }
//
//        return binding.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        this.year = year
//        this.month = month
//        this.day = dayOfMonth
//
//        // can this be done different ? nevermind though, this is just Android, it's not that important
//            // to have good design; it's not my language, it's not my system, it's just an app
//
//        Calendar.getInstance().also {
//            TimePickerDialog(requireContext(), this,
//                it.get(Calendar.HOUR),
//                it.get(Calendar.MINUTE),
//                true).show()
//        }
//    }
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        this.hour = hourOfDay
//        this.minute = minute
//
//        binding.PopUpDialogTextView.text = "Year: $year, Month: $month, Day: $day, Hour: $hour, Minute: $minute"
//    }
//
//}