package ro.rsbideveloper.rsbi.ui

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "database1"

        const val TABLE_DATA1 = "table1"
        const val TABLE_DATA1_KEY_ID = "id"
        const val TABLE_DATA1_KEY_NAME = "name"
        const val TABLE_DATA1_KEY_EMAIL = "email"

        const val TABLE_DATA2 = "table1"
        const val TABLE_DATA2_KEY_ID = "id"
        const val TABLE_DATA2_KEY_attr1 = "attr1"
        const val TABLE_DATA2_KEY_attr2 = "attr2"
        const val TABLE_DATA2_KEY_attr3 = "attr3"

        // ... etc.
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if(db == null) {
            Log.d("DATABASEHANDLER", "db: SQLiteDatabase? is null in onCreate(); cannot create database")
        }

        db?.execSQL(
            "CREATE TABLE $TABLE_DATA1($TABLE_DATA1_KEY_ID INTEGER PRIMARY KEY, $TABLE_DATA1_KEY_NAME TEXT, $TABLE_DATA1_KEY_EMAIL TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // <TODO> you can do better than this ! basically: try to recover some of the data ..? or don't

        if(db == null) {
            Log.d("DATABASEHANDLER", "db: SQLiteDatabase? is null in onUpgrade(), and it shouldn't be")
        }

        db?.let {
            it.execSQL(
                "DROP TABLE IF EXISTS $TABLE_DATA1"
            )
            onCreate(db)
        }
    }

    fun insert(data: SQLiteDataClass): Long {
        val content = ContentValues()
        content.put(TABLE_DATA1_KEY_NAME, data.name)
        content.put(TABLE_DATA1_KEY_EMAIL, data.email)

        val result = writableDatabase.insert(TABLE_DATA1, null, content)
        writableDatabase.close()
        return result
    }

    fun selectAll(table: String): ArrayList<SQLiteDataClass> {
        var cursor: Cursor? = null
        val result = ArrayList<SQLiteDataClass>()

        try {
            cursor = readableDatabase.rawQuery("SELECT * FROM $table", null)

        } catch(e: Exception) {
            Log.d("DATABASEHANDLER", "Select all query has failed somehow: ${e.message}")

            val temp = readableDatabase.execSQL("SELECT * FROM $table")
            Log.d("DATABASEHANDLER", "rawQuery() failed, used execSQL() and it returned $temp") // <TODO> what the actual f'() is this about ?

            return ArrayList<SQLiteDataClass>()
        }

        if(cursor.moveToFirst()) {
            try {
                do {
                    result.add(
                        SQLiteDataClass(
                            cursor.getInt(cursor.getColumnIndex(TABLE_DATA1_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TABLE_DATA1_KEY_NAME)),
                            cursor.getString(cursor.getColumnIndex(TABLE_DATA1_KEY_EMAIL))
                        ))

                } while (cursor.moveToNext())

            } catch (e: Exception) {
                Log.d("DATABASEHANDLER", "Cursor getColumnIndex might have returned a null: ${e.message}")
            }
        }

        return result
    }

    fun selectByQuery(query: String): ArrayList<SQLiteDataClass> {
        var cursor: Cursor? = null
        val result = ArrayList<SQLiteDataClass>()

        try {
            cursor = readableDatabase.rawQuery(query, null)

        } catch(e: Exception) {
            Log.d("DATABASEHANDLER", "Used query failed: $query\nException: ${e.message}")

            val temp = readableDatabase.execSQL(query)
            Log.d("DATABASEHANDLER", "rawQuery() failed, used execSQL() and it returned $temp")

            return ArrayList<SQLiteDataClass>()
        }

        if(cursor.moveToFirst()) {
            try {
                do {
                    result.add(
                        SQLiteDataClass(
                            cursor.getInt(cursor.getColumnIndex(TABLE_DATA1_KEY_ID)),
                            cursor.getString(cursor.getColumnIndex(TABLE_DATA1_KEY_NAME)),
                            cursor.getString(cursor.getColumnIndex(TABLE_DATA1_KEY_EMAIL))
                    ))

                } while (cursor.moveToNext())

            } catch (e: Exception) {
                Log.d("DATABASEHANDLER", "Cursor getColumnIndex might have returned a null: ${e.message}")
            }
        }

        return result
    }

    fun update(table: String, data: SQLiteDataClass): Int {
        val content = ContentValues()
        content.put(TABLE_DATA1_KEY_NAME, data.name)
        content.put(TABLE_DATA1_KEY_EMAIL, data.email)

        val result = writableDatabase.update(table, content,
            "$TABLE_DATA1_KEY_ID=${data.id}",
            null
        )

        writableDatabase.close()
        return result
    }

    fun deleteById(table: String, id: Int): Int {
        val result = writableDatabase.delete(table,
            "$TABLE_DATA1_KEY_ID=$id",
            null)

        writableDatabase.close()
        return result
    }

    fun deleteByColumns(table: String, hashMap: HashMap<String, Any>): Int {   // <TODO> maybe take a HashMap as input; I think I need another hashMap to parametrize the types for each column as well
        return -1
    }

    fun deleteByObject(table: String, data: SQLiteDataClass): Int {
        // <TODO> generate the query based on the data class -> annotation processors are good for this actually
//        writableDatabase.delete(table, )
        return -1
    }

}