package ro.rsbideveloper.rsbi.test.recyclerViewAdapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.rsbideveloper.rsbi.test.recyclerViewHolders.NestedRecyclerViewsLayout2Holder

class NestedRecyclerViewsLayout2Adapter(
    private val context: Context,
    private val list: List<String>
) : RecyclerView.Adapter<NestedRecyclerViewsLayout2Holder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NestedRecyclerViewsLayout2Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NestedRecyclerViewsLayout2Holder, position: Int) {
        TODO("Not yet implemented")
        // <TODO> so the recycler view gets initialized here or in onCreate ?
    }

    override fun getItemCount(): Int {
        return list.size
    }
}