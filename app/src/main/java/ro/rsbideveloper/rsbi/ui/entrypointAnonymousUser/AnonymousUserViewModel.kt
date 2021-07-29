package ro.rsbideveloper.rsbi.ui.entrypointAnonymousUser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.data.article.Article
import ro.rsbideveloper.rsbi.data.article.ArticleDao
import ro.rsbideveloper.rsbi.data.article.ArticleDatabase

class AnonymousUserViewModel(application: Application) : AndroidViewModel(application) {
    private var initialSynchronizationOccured = false

    private val database: ArticleDatabase = ArticleDatabase.getDatabase(application)
    private val databaseDao: ArticleDao = database.getDao()
    val databaseData: LiveData<List<Article>> = databaseDao.selectAll()

    private val urlsArticles: List<String> = application.resources.getStringArray(R.array.articles_urls).toList()
//    private val urls


    fun initialSynchronizationWithRemoteServer() {
        if(!initialSynchronizationOccured) {
            GlobalScope.launch(Dispatchers.IO) {
                downloadArticles(urlsArticles)
                // <TODO> the other parts of the website to be downloaded and parsed
            }
            initialSynchronizationOccured = true
        }
    }

    // <TODO> other kinds of data mining
    private suspend fun downloadArticles(urls: List<String>) {
        for(url in urls) {
            checkUrlAndDownloadArticles(url)
        }
    }

    private suspend fun checkUrlAndDownloadArticles(url: String) {
        Log.d("SYNCHRONIZE", "Checking update time")

        Log.d("SYNCHRONIZE", "Synchronizing $url")
        downloadURLToHTML(url)?.let {
            parseForArticlesFromHTML(it, url)
        }
    }

    private suspend fun downloadURLToHTML(url: String): String? {
        val client = HttpClient(CIO)
        val response: HttpResponse = client.get(url)
        val content = StringBuilder()
        var line: String? = ""

        while(line != null) {
            content.append(line)
            line = response.content.readUTF8Line(65536) // <TODO> what to do about this hard-coded value (which isn't even constrained by much, by design) ? (64KB)
        }
        client.close()

        return if(content.isNotEmpty()) {
            content.toString()
        } else {
            null
        }
    }

    private suspend fun parseForArticlesFromHTML(codeHTML: String, sourceURL: String) {
        val document = Jsoup.parse(codeHTML)
        val articles = document.body().select("article")

        for(article in articles) {
            val title = article.select(".entry-title a").attr("title")
            val detailedArticleURL = article.select(".entry-title a").attr("href")
            val imgURL = article.select(".card-image img").attr("src")  // <TODO> might be missing, not all articles have a photo -> placeholders images based on ?? (category ? author ? title ?)
            val content = article.select(".entry-summary p").text()
                .toString().replace("Read more…", "", true) // <TODO> remove "Read more"
            val category = article.select(".category").text()
            val categoryURL = article.select(".category").attr("href")  // <TODO> missing
            val author = article.select("div .author a").attr("title")  // <TODO> there is the alternative with <b>.text()
            val authorURL = article.select("div .author a").attr("href")
            val creationTime = article.select(".entry-date").attr("datetime").toString()
                .replace("T", " ", true)
                .replace("+", " GMT+", true)
            val latestModificationTime = article.select(".updated").attr("datetime").toString()
                .replace("T", " ", true)
                .replace("+", " GMT+", true)


            // <TODO> instead of adding / checking / synchronizing data here, instead check in with the database

            val extractedArticle = Article( sourceURL, title, detailedArticleURL, imgURL,
                                            content, category, categoryURL, author,
                                            authorURL, creationTime, latestModificationTime)
            // <TODO> check if article exists (result must be either singular or empty; if it's singular then update, if it's empty then insert; there cannot be multiple, because it's the PrimaryKey being used for identification / the query)
//            databaseDao. -> a specific query !
            val result: List<Article> = listOf()

            when {
                result.isEmpty() -> {
                    databaseDao.insert(extractedArticle)
                }
                result.size == 1 -> {
                    databaseDao.update(extractedArticle)
                }
                else -> {
                    Log.d("SYNCHRONIZE", "Error state, more articles in query result than expected: ${result}")
                }
            }
        }

        // <TODO> now check whether there are further Next pages; find them, extract the href's,
            // have a list of recursive calls to extract the html and extract articles and further next's, but be wary
            // of cycles (implement cycle detection as well for a granularity of a session of synchronization (although it
            // shouldn't be strictly necessary)

        val nextHref = document.body().select("nav .nav-links .next").attr("href")
        if(nextHref.isNotEmpty()) {
            checkUrlAndDownloadArticles(nextHref)
        }
    }







    // HTML
    // Articles
    // Images

    // Repo, Database, Dao, ..?
    // Sync data with remote server, async propagate data through app (callbacks, LiveData)

    private val defaultLiveState1 = "0" // <TODO> initial state, how do you decide this ? (get it from a persistent source, otherwise use the default)

    public val liveState1 by lazy {    // <TODO> why use lazy initialization, is it mandatory ?
        MutableLiveData<String>(defaultLiveState1) }

    // <TODO> I think that state changes should be modularized in this class, to maintain integrity; so users / consumers of
        // this ViewModel would instead have to call methods presented by this class, for changing state

    public fun changeState1() {
        liveState1.value += "0"
    }
}