package ro.rsbideveloper.rsbi.data.article

data class Article (
    val title: String,  // div.h2
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