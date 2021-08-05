package ro.rsbideveloper.rsbi.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ro.rsbideveloper.rsbi.databinding.NestedRecyclerViewsActivityBinding
import ro.rsbideveloper.rsbi.test.recyclerViewAdapters.NestedRecyclerViewsLayout1Adapter
import ro.rsbideveloper.rsbi.test.viewModels.NestedRecyclerViewsViewModel

class NestedRecyclerViews : AppCompatActivity() {

    private lateinit var binding: NestedRecyclerViewsActivityBinding
    private lateinit var viewModel: NestedRecyclerViewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NestedRecyclerViewsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = NestedRecyclerViewsViewModel()

        binding.NestedRecyclerViewsRootRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@NestedRecyclerViews)
            adapter = NestedRecyclerViewsLayout1Adapter(this@NestedRecyclerViews, viewModel.list1)
            // <TODO> set onClick listeners ? they will be nested (!), so how is focus / clickability get handled in this case (?!)

            // <TODO> what does this do / look like ?
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
        }
    }
}