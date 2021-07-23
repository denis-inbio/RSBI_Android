package ro.rsbideveloper.rsbi.MVVM.pageHTML

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PageHTML::class], version = 1, exportSchema = false)
abstract class PageHTMLDatabase : RoomDatabase() {
    abstract fun getDao(): PageHTMLDao

    companion object {
        @Volatile
        private var SINGLETON: PageHTMLDatabase? = null

        public fun getDatabase(context: Context) : PageHTMLDatabase {
            if(SINGLETON != null) {
                return SINGLETON as PageHTMLDatabase
            } else {
                synchronized(this) {
                    SINGLETON = Room.databaseBuilder(
                        context.applicationContext,
                        PageHTMLDatabase::class.java,
                        "event_database")
                        .build()
                    return SINGLETON as PageHTMLDatabase
                }
            }
        }
    }
}