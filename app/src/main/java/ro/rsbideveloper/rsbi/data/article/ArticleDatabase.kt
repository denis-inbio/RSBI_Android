package ro.rsbideveloper.rsbi.data.article

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getDao(): ArticleDao

    companion object {
        @Volatile
        private var SINGLETON: ArticleDatabase? = null

        public fun getDatabase(context: Context) : ArticleDatabase {
            if(SINGLETON != null) {
                return SINGLETON as ArticleDatabase
            } else {
                synchronized(this) {
                    SINGLETON = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        "article_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    return SINGLETON as ArticleDatabase
                }
            }
        }
    }
}