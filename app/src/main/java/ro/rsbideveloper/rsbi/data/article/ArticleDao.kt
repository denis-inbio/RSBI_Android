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


    /// For flexible Queries

    @Query("SELECT * FROM article_table WHERE category LIKE :category")
    fun selectByCategoryLiveData(category: String): LiveData<List<Article?>>

    @Query("SELECT * FROM article_table WHERE title LIKE :title")
    fun selectByTitleLiveData(title: String): LiveData<List<Article?>>

    @Query("SELECT * FROM article_table WHERE author LIKE :author")
    fun selectByAuthorLiveData(author: String): LiveData<List<Article?>>

    @Query("SELECT * FROM article_table WHERE creationTime LIKE :creationTime")
    fun selectByDatetimeLiveData(creationTime: String): LiveData<List<Article?>>

    @Query("SELECT * FROM article_table WHERE LOWER(category) LIKE LOWER(:query) OR LOWER(title) LIKE LOWER(:query) OR LOWER(author) LIKE LOWER(:query) OR LOWER(creationTime) LIKE LOWER(:query)")
    fun selectByCategoryOrTitleOrAuthorOrDatetimeLiveData(query: String): LiveData<List<Article?>>

}