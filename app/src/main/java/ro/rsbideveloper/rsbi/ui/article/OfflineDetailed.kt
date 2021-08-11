package ro.rsbideveloper.rsbi.ui.article

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ro.rsbideveloper.rsbi.MainActivityViewModel
import ro.rsbideveloper.rsbi.R

class OfflineDetailed: Fragment(R.layout.article_event_detailed_article_form) {
    private lateinit var viewModel: MainActivityViewModel
    private val args: OfflineDetailedArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireContext().applicationContext as Application).create(
                MainActivityViewModel::class.java)


        /// findViewById
        val ArticleEventDetailedForm_root = view.findViewById<ScrollView>(R.id.ArticleEventDetailedForm_root)
        val ArticleEventDetailedForm_root_constraint = view.findViewById<ConstraintLayout>(R.id.ArticleEventDetailedForm_root_constraint)
        val ArticleEventDetailedForm_root_constraint_featuredImage = view.findViewById<ImageView>(R.id.ArticleEventDetailedForm_root_constraint_featuredImage)
        val ArticleEventDetailedForm_root_constraint_category = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_category)
        val ArticleEventDetailedForm_root_constraint_title = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_title)
        val ArticleEventDetailedForm_root_constraint_publishedDatetime = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_publishedDatetime)
        val ArticleEventDetailedForm_root_constraint_author = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_author)
        val ArticleEventDetailedForm_root_constraint_content = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_content)
        val ArticleEventDetailedForm_root_constraint_lastEditedDatetime = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_lastEditedDatetime)
        val ArticleEventDetailedForm_root_constraint_comments = view.findViewById<TextView>(R.id.ArticleEventDetailedForm_root_constraint_comments)
        // <TODO> maybe a button to change the language (en <-> ro) and a TextView that shows the current language being displayed

        /// make the database query
        val article = viewModel.selectArticleByPrimaryKey(args.primaryKey)

        /// handle edge cases; show that you are in offline mode; maybe article is null / not yet loaded

        /// set the data + Observer
//        val featuredImage
//        val category =
    }
}