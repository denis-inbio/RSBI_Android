package ro.rsbideveloper.rsbi.data.article

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article (
    val sourceURL: String,  // the algorithm (/ scope)
    val title: String,  // div.h2
    @PrimaryKey
    val detailedArticleURL: String, // h2.a, a*.img, p.a, div.a.a
    val imgURL: String, // img.src <TODO> caching the actual image
    val content: String,    // div.p
    val category: String,   // div.h6
    val categoryURL: String,    // h6.a
    val author: String, // div.a
    val authorURL: String,  // div.a (same, among attributes)
    val creationTime: String,   // div.a.a.time1
    val latestModificationTime: String  // div.a.a.time2
)