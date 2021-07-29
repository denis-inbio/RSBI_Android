package ro.rsbideveloper.rsbi.ui.entrypointAnonymousUser

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import ro.rsbideveloper.rsbi.data.article.ArticleRecyclerViewAdapter
import ro.rsbideveloper.rsbi.data.article.ArticleRecyclerViewDecoration
import ro.rsbideveloper.rsbi.databinding.AnonymousUserPerspectiveBinding

// <TODO> change blue action button in keyboard (~ Imm ?)
// <TODO> the act of getting data from the remote server needs to be out of the activity or it gets triggered too often on configuration change
// <TODO> also, getting the data is not yet asynchronous; and it also needs to have a smaller temporal granularity for synchronizing the LiveData<> with the suspend function that gets the data; also, first check the caches, then try to get check for synchronization and eventually also get the new data (or eliminate pre-existing data !)

// <TODO> try to synchronize with the server every 15 minutes or so, after the app has been turned on
    // (*?) what about marking an article with a ~ "New article" sign, when freshly synchronized for the first time ?

class AnonymousUserPerspective : AppCompatActivity() {
    private lateinit var binding: AnonymousUserPerspectiveBinding
    private lateinit var viewModel: AnonymousUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AnonymousUserPerspectiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("NAVIGATION",
            binding.AnonymousUserPerspectiveRootNavHost.findNavController().toString()
        )

        viewModel = ViewModelProvider(this).get(AnonymousUserViewModel::class.java)
        viewModel.initialSynchronizationWithRemoteServer()
    }

//    private fun initiateArticlesList() {
//        binding..apply {
//            this.layoutManager =
//                LinearLayoutManager(this@AnonymousUserPerspective)    // <TODO> this seems to be used when $this itself is shadowed and an instance other than the "closest" is needed
//
//            this.adapter = viewModel.databaseData.value?.let {
//                ArticleRecyclerViewAdapter(this@AnonymousUserPerspective, it) }
//
//            addItemDecoration(ArticleRecyclerViewDecoration(30))
//
//            viewModel.databaseData.observe(this@AnonymousUserPerspective, Observer { databaseData ->
//                this.adapter.let { adapter ->
//                    (adapter as ArticleRecyclerViewAdapter).setList(databaseData) }
//            })
//        }
//    }
}