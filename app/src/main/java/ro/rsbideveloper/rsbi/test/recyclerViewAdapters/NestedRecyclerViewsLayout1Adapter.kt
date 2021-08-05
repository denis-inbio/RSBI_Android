package ro.rsbideveloper.rsbi.test.recyclerViewAdapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.rsbideveloper.rsbi.test.recyclerViewHolders.NestedRecyclerViewsLayout1Holder

class NestedRecyclerViewsLayout1Adapter(
    private val context: Context,
    private val list: List<String>
) : RecyclerView.Adapter<NestedRecyclerViewsLayout1Holder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NestedRecyclerViewsLayout1Holder {
        TODO("Not yet implemented")
        // <TODO> will have to somehow generate a List<String> and initialize a recycler view in here
    }

    override fun onBindViewHolder(holder: NestedRecyclerViewsLayout1Holder, position: Int) {
        TODO("Not yet implemented")
        // <TODO> or do I do that here ?
    }

    override fun getItemCount(): Int {
        return list.size
    }
}