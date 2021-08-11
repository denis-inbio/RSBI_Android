package ro.rsbideveloper.rsbi.data.article

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

    @Update
    suspend fun update(article: Article)

//     <TODO> WARNING: do not use this during ViewModel's initialization, as it will crash the app
    @Query("SELECT * FROM article_table ORDER BY creationTime DESC")
    fun selectAll(): List<Article?>

    @Query("SELECT * FROM article_table ORDER BY creationTime DESC")
    fun selectAllLiveData(): LiveData<List<Article?>>

    // <TODO> for some reason though, when this method is called, it seems to be fine (?)
    @Query("SELECT * FROM article_table WHERE detailedArticleURL = :detailedArticleURL")
    fun selectById(detailedArticleURL: String): Article?

    @Query("SELECT * FROM article_table WHERE detailedArticleURL = :detailedArticleURL")
    fun selectByIdLiveData(detailedArticleURL: String): LiveData<Article?>
}