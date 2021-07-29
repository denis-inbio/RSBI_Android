package ro.rsbideveloper.rsbi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ro.rsbideveloper.rsbi.R

class SQLiteAdapter(
    private val context: Context,
    private var list: List<SQLiteDataClass>
) : RecyclerView.Adapter<SqliteViewHolder>() {

    fun setList(list: List<SQLiteDataClass>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SqliteViewHolder {
        return SqliteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.sqlite_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SqliteViewHolder, position: Int) {
        val data = list[position]

        if(position % 2 == 0) {
            holder.root.setBackgroundColor(
                ContextCompat.getColor(context, R.color.design_default_color_primary)
            )
        } else {
            holder.root.setBackgroundColor(
                ContextCompat.getColor(context, R.color.design_default_color_secondary)
            )
        }

        holder.name.text = data.name
        holder.email.text = data.email

        holder.edit.setOnClickListener {
            if(context is SQLiteMain) { // <TODO> another kind of "design modularity" through some sort of parametrization based on type information
                context.editDialog(data)    // so it's a reference to the data in the list ?
            }
        }

        holder.delete.setOnClickListener {
            if(context is SQLiteMain) {
                context.deleteDialog(data)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class SqliteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val root: ConstraintLayout = view.findViewById(R.id.SqliteListItem_root)    // <TODO> ??

    val name: TextView = view.findViewById<TextView>(R.id.SqliteListItem_root_tvName)
    val email: TextView = view.findViewById<TextView>(R.id.SqliteListItem_root_tvEmail)
    val edit: ImageView = view.findViewById<ImageView>(R.id.SqliteListItem_root_ivEdit)
    val delete: ImageView = view.findViewById<ImageView>(R.id.SqliteListItem_root_ivDelete)
}
