package ro.rsbideveloper.rsbi.ui.article

import android.app.Application
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ro.rsbideveloper.rsbi.MainActivityViewModel
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.data.article.Article
import ro.rsbideveloper.rsbi.data.article.RecyclerViewAdapter
import ro.rsbideveloper.rsbi.data.article.RecyclerViewDecoration

// <TODO> the possibility for there to be no posts (especially when filtering), so have the Ui for that as well
// <TODO> as long as whenever Observe is triggered I cannot prevent the recycler view from resetting the position,
    // I will avoid using LiveData here and instead introduce a forced wait until the data is downloaded
// <TODO> language switch button ? (Ro, Eng)

class SearchList: Fragment(R.layout.articles_search_list) {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var observerEmpty: Observer<List<Article?>>
    private lateinit var observerSearch: Observer<List<Article?>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /// Observer
        observerEmpty = Observer { list ->
            Log.d("SEARCHLIST", "Logging initializeRecyclerView(): triggered the empty-case Observer")

            view.findViewById<RecyclerView>(R.id.ArticlesSearchList_root_recyclerView).also { recycler ->
                    (recycler?.adapter as RecyclerViewAdapter?)?.setList(list)
                }
        }

        observerSearch = Observer { list ->
            Log.d("SEARCHLIST", "Logging initializeRecyclerView(): triggered the search-case Observer")

            view.findViewById<RecyclerView>(R.id.ArticlesSearchList_root_recyclerView).also { recycler ->
                (recycler?.adapter as RecyclerViewAdapter?)?.setList(list)
            }
        }

        /// ViewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireContext().applicationContext as Application).create(
                MainActivityViewModel::class.java)

        /// Ui
        initializeRecyclerView(view)
        configureSoftKeyboard(view)
        configureSearchBar(view)

    }

    private fun initializeRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.ArticlesSearchList_root_recyclerView).also { recycler ->
            Log.d("SEARCHLIST", "Logging initializeRecyclerView(): initializing RecyclerView")
            recycler?.layoutManager = LinearLayoutManager(context)
            recycler?.adapter = RecyclerViewAdapter(requireContext(), listOf())
            recycler?.addItemDecoration(RecyclerViewDecoration(16))
        }

        viewModel.liveArticles.observe(viewLifecycleOwner, observerEmpty)
    }

    private fun configureSoftKeyboard(view: View) {
        // virtual keyboard when writing a search query -> the blue active button
        // <TODO> listen for imeDone() ?

    }

    private fun configureSearchBar(view: View) {
        view.findViewById<TextInputEditText>(R.id.ArticlesSearchList_root_inputLayout_editText)
            .doOnTextChanged { text, start, before, count ->
                if (text != null && text.isEmpty()) {
                    viewModel.removeObserversFromQueryLiveData(viewLifecycleOwner)
                    viewModel.liveArticles.observe(viewLifecycleOwner, observerEmpty)

                } else if (text != null && !text.isEmpty()) {

                    // <TODO> check if this is done properly; actually, I only want to remove a specific Observer, but how
                    viewModel.liveArticles.removeObservers(viewLifecycleOwner)
                    viewModel.selectArticlesByQuery(text.toString()).observe(viewLifecycleOwner, observerSearch)
                }
            }
    }

    private fun configureButtons(view: View) {
        view.findViewById<ImageButton>(R.id.ArticlesSearchList_root_btnSync)
            .setOnClickListener { button ->
                viewModel.synchronize()
                // TODO Ui indication that synchronization is in progress or finished, such as replacing the button with
                //  a progress indication (also prevents multiple calls that do the same thing)
            }

    }
}
