package ro.rsbideveloper.rsbi.data.detailedArticle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailed_article_table")
data class DetailedArticle (
    val sourceURL: String,  // the algorithm (/ scope)
    val title: String,  // div.h2
    @PrimaryKey
    val contentHTML: String,    // div.p
)