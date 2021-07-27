package ro.rsbideveloper.rsbi.data.pageHTML

import androidx.lifecycle.LiveData

class PageHTMLRepository(private val dao: PageHTMLDao) {
    val data: LiveData<List<PageHTML>> = dao.selectAll()

    suspend fun insert(pageHTML: PageHTML) {
        dao.insert(pageHTML)
    }

    suspend fun update(pageHTML: PageHTML) {
        dao.update(pageHTML)
    }

}