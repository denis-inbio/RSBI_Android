package ro.rsbideveloper.rsbi.MVVM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Query("SELECT * FROM event_table ORDER BY id ASC")
    fun selectAll() : LiveData<List<Event>>
}