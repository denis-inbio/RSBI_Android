package ro.rsbideveloper.rsbi.test.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import ro.rsbideveloper.rsbi.R

class NestedRecyclerViewsViewModel: ViewModel() {
    // <TODO> turn these into LiveData<>; how to edit them ? and what does it mean to define a "custom" LiveData<> class ? (and define its onActive and its onInactive)
    val list1: List<String> = listOf("First title", "Second title", "Third title")
    val list2: List<String> = listOf("First sub-title", "Second sub-title", "Third sub-title", "Fourth sub-title", "Fifth sub-title")

    init {

    }

    fun loadData(context: Context) {
        // <TODO> loading a file and parsing the JSON; but what is the raw folder about ? why is it located in the
            // raw folder when there is an asset folder ?
        val resource = context.resources.openRawResource(R.raw.data).bufferedReader().use {
            it.readLine()
        }
    }
}