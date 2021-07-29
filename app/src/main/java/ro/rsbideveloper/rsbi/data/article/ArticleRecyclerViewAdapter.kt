package ro.rsbideveloper.rsbi.data.article

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ro.rsbideveloper.rsbi.R

class ArticleRecyclerViewAdapter(
    private val context: Context,
    private var list: List<Article>   // <TODO> change this to be a LiveData<>; also, the class needs to set / register an observer to this LiveData<> instance
                                                // <TODO> but where does the LiveData<> live ? and what is its lifecycle like ? I want it to exist for the duration of the initiating activity (InteractionOne); it will be populated upon activity start, from a persistent database, through a query
) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    public fun setList(list: List<Article>) {
        this.list = list
    }

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
        list.also {
            holder.ArticleEventListForm_root_title.text = it[position].title
            holder.ArticleEventListForm_root_datetime.text = it[position].creationTime

            if(it[position].imgURL.isNotEmpty()) {
                Glide.with(context)
                    .load(it[position].imgURL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_search_24)
                    .into(holder.ArticleEventListForm_root_featuredImage)
            } else {    // <TODO> actually, based on the category, use different default images; for example, jobs will have a specific logo / featured image, other articles / posts which do not have a parameter imgURL will have their own based on category
                when(it[position].category) {   // <TODO> also, this will need some sort of case insensitive, approximate (string) matching algorithm
                    "JOBS" -> { // <TODO> and these will have to be defined for both english and romanian (so it scales badly with language), unless I can use multiple patterns with a case in when() Kotlin (!?)
                        holder.ArticleEventListForm_root_featuredImage.setImageDrawable(
                            AppCompatResources.getDrawable(holder.itemView.context, R.drawable.ic_baseline_rss_feed_24))    // <TODO> so, get some drawables into the project (!)
                    }
                    "Other" -> {

                    }
                    // <TODO> the default case for when() in Kotlin
                }
            }

            holder.ArticleEventListForm_root.setOnClickListener {
                Toast.makeText(context, "Clicked an item at position $position", Toast.LENGTH_SHORT)
                    .show()
                // findNavController()
                //            holder.itemView.findNavController().navigate(Events_pageDirections
                //                .actionEventsPageNavToWriteEventPageNav(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}


