package ro.rsbideveloper.rsbi.MVVM.pageHTML

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localPagesHTML")
data class PageHTML (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
//    val retrievalDate: DateTime,
    val URL: String,
    val HTML: String
)