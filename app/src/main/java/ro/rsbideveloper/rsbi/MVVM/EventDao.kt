package ro.rsbideveloper.rsbi.MVVM.event

import androidx.lifecycle.LiveData
import androidx.room.*

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