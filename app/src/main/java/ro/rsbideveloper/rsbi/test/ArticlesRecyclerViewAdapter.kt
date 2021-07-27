package ro.rsbideveloper.rsbi.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ro.rsbideveloper.rsbi.data.article.Article
import ro.rsbideveloper.rsbi.R

class ArticlesRecyclerViewAdapter(
    private val list: List<Article>,    // <TODO> change this to be a LiveData<>; also, the class needs to set / register an observer to this LiveData<> instance
    private val context: Context        // <TODO> but where does the LiveData<> live ? and what is its lifecycle like ? I want it to exist for the duration of the initiating activity (InteractionOne); it will be populated upon activity start, from a persistent database, through a query
) : RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ArticleEventListForm_root = itemView.findViewById<ConstraintLayout>(R.id.ArticleEventListForm_root)
        val ArticleEventListForm_root_featuredImage = itemView.findViewById<ImageView>(R.id.ArticleEventListForm_root_featuredImage)
        val ArticleEventListForm_root_title = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_title)
        val ArticleEventListForm_root_datetime = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_event_list_form, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.ArticleEventListForm_root_title.text = data.title
        holder.ArticleEventListForm_root_datetime.text = data.creationTime
        Glide.with(context)
            .load(data.imgURL)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_search_24)
            .into(holder.ArticleEventListForm_root_featuredImage)

        holder.ArticleEventListForm_root.setOnClickListener {
            Toast.makeText(context, "Clicked an item at position $position", Toast.LENGTH_SHORT).show()
            // findNavController()
//            holder.itemView.findNavController().navigate(Events_pageDirections
//                .actionEventsPageNavToWriteEventPageNav(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /// <TODO> Async LiveData
//    fun setData(events: List<Event>) {
//        this.dataList = events
//        notifyDataSetChanged()
//    }

}


