package ro.rsbideveloper.rsbi.data.event

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class], version = 2, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun getDao(): EventDao

    companion object {
        @Volatile   // cache coherence between threads
        private var SINGLETON: EventDatabase? = null

        public fun getDatabase(context: Context) : EventDatabase {
            if(SINGLETON != null) {
                return SINGLETON as EventDatabase
            } else {
                synchronized(this) {    // thread-level lock of this (?"shared memory"?) [coherence mechanisms will block access, and force waiting in the other cores]
                    SINGLETON = Room.databaseBuilder(
                        context.applicationContext, // so essentially a database is associated with a context; in this case, any context is used to get access to the "parent, application context"; but how does a context persist across running instances of an Android application ? I assume it gets saved to a file / to disk, somewhere
                        EventDatabase::class.java,
                        "event_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    return SINGLETON as EventDatabase
                }
            }
        }
    }
}