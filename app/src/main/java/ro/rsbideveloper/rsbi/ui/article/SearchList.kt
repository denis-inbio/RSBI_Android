package ro.rsbideveloper.rsbi.ui.article

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jsoup.Jsoup
import ro.rsbideveloper.rsbi.MainActivityViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.data.article.RecyclerViewAdapter
import ro.rsbideveloper.rsbi.data.article.RecyclerViewDecoration
import ro.rsbideveloper.rsbi.data.pageHTML.PageHTML
import ro.rsbideveloper.rsbi.databinding.ArticlesSearchListBinding

// <TODO> the possibility for there to be no posts (especially when filtering), so have the Ui for that as well
// <TODO> as long as whenever Observe is triggered I cannot prevent the recycler view from resetting the position,
    // I will avoid using LiveData here and instead introduce a forced wait until the data is downloaded
// <TODO> language switch button ? (Ro, Eng)

class SearchList: Fragment(R.layout.articles_search_list) {
    private lateinit var viewModel: MainActivityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /// ViewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireContext().applicationContext as Application).create(
                MainActivityViewModel::class.java)

        /// Ui
        initializeRecyclerView(view)
        // virtual keyboard when writing a search query

        val searchQueryTextView = view.findViewById<TextInputEditText>(R.id.ArticlesSearchList_root_inputLayout_editText)
        val searchQuery: String = searchQueryTextView.text.toString()
    }

    private fun initializeRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.ArticlesSearchList_root_recyclerView).also { recycler ->
            Log.d("SEARCHLIST", "Logging initializeRecyclerView(): initializing RecyclerView")
            recycler?.layoutManager = LinearLayoutManager(context)
            recycler?.adapter = RecyclerViewAdapter(requireContext(), listOf())
            recycler?.addItemDecoration(RecyclerViewDecoration(16)) // <TODO> card view elevation like and eventually a slight background coloration that alternates
        }

        viewModel.liveArticles.observe(viewLifecycleOwner, Observer { list ->
            Log.d("SEARCHLIST", "Logging initializeRecyclerView(): triggered the Observer")

            view.findViewById<RecyclerView>(R.id.ArticlesSearchList_root_recyclerView).also { recycler ->
                Log.d("SEARCHLIST", "Logging initializeRecyclerView(): re-setting the list in the Adapter")
                (recycler?.adapter as RecyclerViewAdapter?)?.setList(list)
            }
        })
    }

}
