package ro.rsbideveloper.rsbi.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// (*?) "AndroidViewModel is different from a regular ViewModel, because it contains an `application reference`"
// (*?) discuss about this style of programming where multiple classes hold "references" to the same data (the
    // data itself basically lives on the heap and will persist for as long as some other object{s} hold{s}
    // {a} reference{s} to it); this also implies that objects can appear and disappear, pairs can become
    // singletons, the Repository generates the data and passes it to the ViewModel, but then the Repository
    // might be destructed and the data would still persist due to its reference in the ViewModel (even more so,
    // the ViewModel would hold callbacks to observers, which could get destructed or new ones added)
class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val data: LiveData<List<Event>>
    private val repository: EventRepository // (*?) I'd rather this have "repositorySQL" as its identifier

    init {  // (*?) supposedly this is executed first when the instance is created / "when EventViewModel gets `called`"
        // so I guess that if a val gets initialized here, the error will go away ..?; it's nice because that way
            // it allows initialization of a val with logic other than "non-statement expressions" [this means to
            // say that things like iteration become possible, or if()'s or switches, compound statements, etc.]
        val dao = EventDatabase.getDatabase(application).getDao()
        repository = EventRepository(dao)

        // this seems to be a case of "shared reference"; so the Repository uses the reference to do "processing"
            // from the perspective of database data processing; the ViewModel on the other hand uses the reference
            // to process data from the perspective of the "model" (or "`business` / `usage` logic"); this seems to be
            // related to the notion of designing based "responsibilities", because this goes beyond just the notion
            // of "who has access to the information ?" up to the notion of "what does each entity / agent that has information
            // access do with the information ?"
        data = repository.data  // (*?) first of all, any ulterior queries will have to affect this LiveData<>; next,
            // the LiveData<> is an in-memory variant of the database ? (at least to the extent of the Query); but I
            // don't think so, because the LiveData could get modified locally (such as changes in a scope or the view
            // the user sees, and that shouldn't necessarily propagate into the database ? or should it ? well, I guess
            // that scopes ought to contain "database-consequential" and "database-inconsequential" data)
    }

    fun addEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(event)
        }
    }
}