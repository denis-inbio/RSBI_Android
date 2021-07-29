package ro.rsbideveloper.rsbi.data.article

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ArticleRecyclerViewDecoration(
    private val marginTop: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = marginTop
    }
}