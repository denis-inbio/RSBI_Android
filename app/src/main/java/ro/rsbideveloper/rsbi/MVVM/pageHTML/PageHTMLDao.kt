package ro.rsbideveloper.rsbi.MVVM.pageHTML

import androidx.lifecycle.LiveData
import androidx.room.*
import ro.rsbideveloper.rsbi.MVVM.event.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("SELECT * FROM event_table ORDER BY id ASC")
    fun selectAll(): LiveData<List<Event>>

    @Query("SELECT * FROM event_table WHERE id= :selectId")
    fun selectById(selectId: Int): LiveData<Event>
}

@Dao
interface PageHTMLDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    add a new page (could the client app somehow discover new pages, starting from some "root page{s}" / URL{s} ?)
    suspend fun insert(pageHTML: PageHTML)

    @Update
//    the logic has already decided to replace the page (so it is an "update") -> overwrite entry, based on id and content
    suspend fun update(pageHTML: PageHTML)

    @Query("SELECT * FROM localPagesHTML")
    fun selectAll(): LiveData<List<PageHTML>>

    // <TODO> keeps complaining that it doesn't know how to implement / "inject" this
//    @Query("SELECT * FROM localPagesHTML WHERE URL = :URL")
////    get specific [hard coded names ?] [or use the URL itself as the conditional]
//    // do check that there is only one match; even better, at some point change the
//    // URL to a PrimaryKey
//    fun selectByURL(URL: String): LiveData<PageHTML>
}