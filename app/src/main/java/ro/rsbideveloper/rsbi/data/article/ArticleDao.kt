package ro.rsbideveloper.rsbi.data.article

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Update
    suspend fun update(article: Article)

    @Query("SELECT * FROM article_table ORDER BY detailedArticleURL ASC")   // ORDER BY creationTime DESC; could it be that the primary key has an index and the other things do not have an index yet ?
    fun selectAll(): LiveData<List<Article>>

    // <TODO> keeps complaining that it doesn't know how to implement / "inject" this
//    @Query("SELECT * FROM event_table WHERE id= :selectId")
//    fun selectById(selectId: Int): LiveData<Article>
}