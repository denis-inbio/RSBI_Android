package ro.rsbideveloper.rsbi.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.SqliteMainBinding

class SQLiteMain : AppCompatActivity() {
    private lateinit var binding: SqliteMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SqliteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        synchronizeRecyclerView()

        // <TODO> something about this sucks; it doesn't effectively "commit" the data insertion or the data update to the RecyclerView's list
            // unless some category of events happen first (like tapping into another field, tapping the back button, clicking an ok, tapping
            // outside the field, the keyboard appears or disappears, ... basically ~ "focus changes"

        binding.sqliteMainRootBtnSubmit.setOnClickListener {
            SQLiteDatabaseHandler(this).also { database ->
                binding.sqliteMainRootInputName.text?.let { name ->
                    binding.sqliteMainRootInputEmail.text?.let { email ->
                        if (name.isNotEmpty() && email.isNotEmpty()) {
                            val statusCode = database.insert(SQLiteDataClass(0, name.toString(), email.toString()))
//                            database.flush()  // <TODO> is there something like this ? is the insert() asynchronous or synchronous ?

                            // <TODO> logic based on success or failure
                            if(statusCode > -1) {
                                // <TODO> reset Ui state (if desired)
                                name.clear()
                                email.clear()

                                synchronizeRecyclerView()
                            } else {
                                Log.d("SQLITEMAIN", "Failed to insert data in database, status code $statusCode")
                            }
            } } } }
        }

    }

    fun editDialog(data: SQLiteDataClass) {
        val dialog = android.app.Dialog(this, R.style.Theme_AppCompat_Dialog)
        dialog.setContentView(R.layout.edit_dialog)
        dialog.setCancelable(false)

        val root = dialog.findViewById<ConstraintLayout>(R.id.EditDialog_root)
        val btnConfirm = dialog.findViewById<Button>(R.id.EditDialog_root_btnConfirm)
        val btnCancel = dialog.findViewById<Button>(R.id.EditDialog_root_btnCancel)
        val nameInputLayout = dialog.findViewById<TextInputLayout>(R.id.EditDialog_root_inputLayout_name)
        val name = dialog.findViewById<TextInputEditText>(R.id.EditDialog_root_name)
        val emailInputLayout = dialog.findViewById<TextInputLayout>(R.id.EditDialog_root_inputLayout_email)
        val email = dialog.findViewById<TextInputEditText>(R.id.EditDialog_root_email)

        name.setText(data.name)
        email.setText(data.email)

        btnConfirm.setOnClickListener {
            name.text?.let { name ->
                email.text?.let { email ->
                    if(name.isNotEmpty() && email.isNotEmpty() &&
                            name.toString() != data.name &&
                            email.toString() != data.email) {   // <TODO> the data actually needs to have changed to commit an update to the database
                        val result = SQLiteDatabaseHandler(this)
                            .update(
                                SQLiteDatabaseHandler.TABLE_DATA1,
                                SQLiteDataClass(data.id, name.toString(), email.toString()))

                        if(result > -1) {
                            // <TODO> synchronize the recycler view
                            synchronizeRecyclerView()
                        } else {
                            Log.d("SQLITEMAIN", "Edit dialog failed, .update() returned $result")
            } } } }

            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun deleteDialog(data: SQLiteDataClass) {
        // <TODO> present a dialog asking ~ "Are you sure you want to delete this ?" (possibly an option of "don't ask me again in the next 10 minutes")
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete entry")
            .setMessage("Are you sure you want to delete this entry ?")
            .setIcon(R.drawable.ic_baseline_warning_24)
            .setPositiveButton("Yes") { dialogInterface, _ ->
                // <TODO> loading progress - start

                val result = SQLiteDatabaseHandler(this).deleteById(SQLiteDatabaseHandler.TABLE_DATA1, data.id)
                if(result > -1) {
                    synchronizeRecyclerView()
                } else {
                    Log.d("SQLITEMAIN", "Delete dialog failed, .update() returned $result")
                }

                // <TODO> loading progress - stop

                dialogInterface.dismiss()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun synchronizeRecyclerView() {
        // <TODO> updating the recycler view's adapter's list -> use LiveData<> instead
        binding.sqliteMainRootRecyclerList.also {
            // <TODO> I don't see why it didn't re-render properly; maybe it still does this unless completly re-initializing the RecyclerView's layoutManager and adapter
            // also, I do not like that it jumps back up whenever it re-initializes anything (be it layoutManager or adapter)

//            (it.adapter as SQLiteAdapter).setList(
//                database.selectAll(SQLiteDatabaseHandler.TABLE_DATA1))
//            it.layoutManager = LinearLayoutManager(this)

            it.adapter = SQLiteAdapter(this, SQLiteDatabaseHandler(this).selectAll(SQLiteDatabaseHandler.TABLE_DATA1))

//            database.flush()  // <TODO> is there something like this ? is the insert() asynchronous or synchronous ?

//            list.addItemDecoration()  // <TODO>

            binding.sqliteMainRootRecyclerListCounter.text = "Number of entries: ${(it.adapter as SQLiteAdapter).itemCount.toString()}"
        }
    }
}