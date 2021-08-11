package ro.rsbideveloper.rsbi.data.article

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.ui.article.SearchListDirections

class RecyclerViewAdapter(
    private val context: Context,
    private var list: List<Article?>   // <TODO> change this to be a LiveData<>; also, the class needs to set / register an observer to this LiveData<> instance
                                                // <TODO> but where does the LiveData<> live ? and what is its lifecycle like ? I want it to exist for the duration of the initiating activity (InteractionOne); it will be populated upon activity start, from a persistent database, through a query
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    fun setList(list: List<Article?>) {
        this.list = list
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ArticleEventListForm_root = itemView.findViewById<ConstraintLayout>(R.id.ArticleEventListForm_root)
        val ArticleEventListForm_root_featuredImage = itemView.findViewById<ImageView>(R.id.ArticleEventListForm_root_featuredImage)
        val ArticleEventListForm_root_category = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_category)
        val ArticleEventListForm_root_title = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_title)
        val ArticleEventListForm_root_author = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_author)
        val ArticleEventListForm_root_datetime = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_event_list_form, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list.also { list ->

            // <TODO> author prefix, title length limit, datetime styling, language dependency, translations, getString() language-dependent
            // <TODO> styling the title, background SVG for category

            val articleBackgroundColors = context.resources.getIntArray(R.array.articleBackgroundColors)
            holder.ArticleEventListForm_root.setBackgroundColor(articleBackgroundColors[position % articleBackgroundColors.size])

            val categoryColors = context.resources.getIntArray(R.array.categoryColors)
            holder.ArticleEventListForm_root_category.text = list[position]?.category
            holder.ArticleEventListForm_root_category.setTextColor(categoryColors[position % categoryColors.size])

            holder.ArticleEventListForm_root_title.text = list[position]?.title
            holder.ArticleEventListForm_root_author.text = "Authored by ${list[position]?.author}"
            holder.ArticleEventListForm_root_datetime.text = "Published on\n${extractDate(list[position]?.creationTime)}"
            // last modified on ..?

            if(list[position]?.imgURL?.isNotEmpty() == true) {
                Glide.with(context)
                    .load(list[position]?.imgURL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_search_24)
                    .into(holder.ArticleEventListForm_root_featuredImage)
            } else {
                Log.d("VIEWHOLDER", "Logging onBindViewHolder(): no imgURL for category ${list[position]?.category}")
            // <TODO> category-specific placeholder images
                // holder.ArticleEventListForm_root_featuredImage.visibility = View.GONE
                when(list[position]?.category) {
                    "RSBI Events" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    "Evenimente externe", "External events" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    "Cariere", "Job offers" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    "Public engagement" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    "RSBI-supported external events" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    "Blog" -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                    else -> {
                        Glide.with(context)
                            .load(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .placeholder(R.drawable.ic_baseline_search_24)
                            .into(holder.ArticleEventListForm_root_featuredImage)
                    }
                }
            }

            // <TODO> decide when to go to the offline version and when to go to the online version
                // or let the offline mode be the main one; for now I want to test the online one for testing`
            holder.ArticleEventListForm_root.setOnClickListener {
                list[position]?.detailedArticleURL?.let{ url ->
                    holder.itemView.findNavController().navigate(SearchListDirections
                        .actionArticlesSearchListToOnlineDetailed(url))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    private fun extractDate(datetimeGMT: String?): String {
        return if(datetimeGMT != null)
            datetimeGMT.substring(0, 10) // inclusive: 9, exclusive: 10
        else
            ""
    }
    private fun extractTime(datetimeGMT: String?): String {
        return if(datetimeGMT != null)
            datetimeGMT.substring(11, 19) // inclusive: 9, exclusive: 10
        else
            ""
    }
    private fun extractGMT(datetimeGMT: String?): String {
        return if(datetimeGMT != null)
            datetimeGMT.substring(23, 29) // inclusive: 9, exclusive: 10
        else
            ""
    }
}


///
///
///


//class RecyclerViewAdapter(
//    private val context: Context,
//    private var list: List<Article>   // <TODO> change this to be a LiveData<>; also, the class needs to set / register an observer to this LiveData<> instance
//    // <TODO> but where does the LiveData<> live ? and what is its lifecycle like ? I want it to exist for the duration of the initiating activity (InteractionOne); it will be populated upon activity start, from a persistent database, through a query
//) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//
//    public fun setList(list: List<Article>) {
//        this.list = list
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val ArticleEventListForm_root = itemView.findViewById<ConstraintLayout>(R.id.ArticleEventListForm_root)
//        val ArticleEventListForm_root_featuredImage = itemView.findViewById<ImageView>(R.id.ArticleEventListForm_root_featuredImage)
//        val ArticleEventListForm_root_title = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_title)
//        val ArticleEventListForm_root_datetime = itemView.findViewById<TextView>(R.id.ArticleEventListForm_root_datetime)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            LayoutInflater
//                .from(parent.context)
//                .inflate(R.layout.article_event_list_form, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        list.also {
//            holder.ArticleEventListForm_root_title.text = it[position].title
//            holder.ArticleEventListForm_root_datetime.text = it[position].creationTime
//
//            if(it[position].imgURL.isNotEmpty()) {
//                Glide.with(context)
//                    .load(it[position].imgURL)
//                    .centerCrop()
//                    .placeholder(R.drawable.ic_baseline_search_24)
//                    .into(holder.ArticleEventListForm_root_featuredImage)
//            } else {    // <TODO> actually, based on the category, use different default images; for example, jobs will have a specific logo / featured image, other articles / posts which do not have a parameter imgURL will have their own based on category
//                when(it[position].category) {   // <TODO> also, this will need some sort of case insensitive, approximate (string) matching algorithm
//                    "JOBS" -> { // <TODO> and these will have to be defined for both english and romanian (so it scales badly with language), unless I can use multiple patterns with a case in when() Kotlin (!?)
//                        holder.ArticleEventListForm_root_featuredImage.setImageDrawable(
//                            AppCompatResources.getDrawable(holder.itemView.context, R.drawable.ic_baseline_rss_feed_24))    // <TODO> so, get some drawables into the project (!)
//                    }
//                    "Other" -> {
//
//                    }
//                    // <TODO> the default case for when() in Kotlin
//                }
//            }
//
//            holder.ArticleEventListForm_root.setOnClickListener {
//                Toast.makeText(context, "Clicked an item at position $position", Toast.LENGTH_SHORT)
//                    .show()
//                // findNavController()
//                //            holder.itemView.findNavController().navigate(Events_pageDirections
//                //                .actionEventsPageNavToWriteEventPageNav(position)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return this.list.size
//    }
//}