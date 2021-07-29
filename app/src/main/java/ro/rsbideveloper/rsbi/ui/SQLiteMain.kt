package ro.rsbideveloper.rsbi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.SqliteMainBinding

class SQLiteMain : AppCompatActivity() {
    private lateinit var binding: SqliteMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SqliteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sqliteMainRootRecyclerList.also { list ->
            list.layoutManager = LinearLayoutManager(this)

//            list.addItemDecoration()  // <TODO>

            SQLiteDatabaseHandler(this).also { database ->
                list.adapter = SQLiteAdapter(this, database.selectAll(SQLiteDatabaseHandler.TABLE_DATA1))
            }
        }

        // <TODO> something about this sucks; it doesn't effectively "commit" the data insertion or the data update to the RecyclerView's list
            // unless some category of events happen first (like tapping into another field, tapping the back button, clicking an ok, tapping
            // outside the field, the keyboard appears or disappears, ... basically ~ "focus changes"

        binding.sqliteMainRootBtnSubmit.setOnClickListener {
            SQLiteDatabaseHandler(this).also { database ->
                binding.sqliteMainRootInputName.text?.let { name ->
                    binding.sqliteMainRootInputEmail.text?.let { email ->
                        if (name.isNotEmpty() && email.isNotEmpty()) {
                            val statusCode = database.insert(SQLiteDataClass(0, name.toString(), email.toString()))

                            // <TODO> logic based on success or failure
                            // <TODO> reset Ui state (if desired)

                            // <TODO> updating the recycler view's adapter's list -> use LiveData<> instead
                            binding.sqliteMainRootRecyclerList.also {
                                (it.adapter as SQLiteAdapter).setList(
                                    database.selectAll(SQLiteDatabaseHandler.TABLE_DATA1)
                                )
                            }
            } } } }
        }

    }

    fun editDialog(data: SQLiteDataClass) {
        val dialog = android.app.Dialog(this, R.style.Theme_AppCompat_Dialog)
        dialog.setCancelable(false)

        dialog.setContentView(R.layout.edit_dialog)
//        dialog.tvName.text = data.name; etc.
        // dialog.btn.setOnClickListener {
    //  val database = DatabaseHandler(this, ...)
    //  if(validate(name, email)) {
//            val status = database.updateData(SQLiteDataClass(data))
//                if(status != -1) {
    //                  manuallyUpdateRecycerView'sList() (!) LiveData<>
    //                }
    // }
        dialog.dismiss()
    }

    fun deleteDialog(data: SQLiteDataClass) {

    }
}