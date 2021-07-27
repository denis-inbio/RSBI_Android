package ro.rsbideveloper.rsbi.data.pageHTML

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PageHTMLViewModel(application: Application) : AndroidViewModel(application) {
    val data: LiveData<List<PageHTML>>
    private val repository: PageHTMLRepository

    init {
        val dao = PageHTMLDatabase.getDatabase(application).getDao()
        repository = PageHTMLRepository(dao)
        data = repository.data
    }

    fun addPageHTML(pageHTML: PageHTML) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(pageHTML) // <TODO> check that id doesn't already exist (based on URL);
                                            // if it exists already, then instead check if it should update()
        }
    }

    fun updatePageHTML(pageHTML: PageHTML) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(pageHTML) // <TODO> check (somewhere) that the page to be updated actually exists
        }
    }

    fun selectPageHTMLById(selectId: Int) { // <TODO> maybe remove this

    }
    fun selectPageHTMLByURL(URL: String) {  // <TODO> for now, the URLs are stored in a <string-array>

    }

}