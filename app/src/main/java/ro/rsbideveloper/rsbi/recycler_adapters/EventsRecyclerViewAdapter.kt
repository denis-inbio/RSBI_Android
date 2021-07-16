package ro.rsbideveloper.rsbi.recycler_adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.events_page_recycler_view_item.view.*
import ro.rsbideveloper.rsbi.MVVM.Event
import ro.rsbideveloper.rsbi.R
import ro.rsbideveloper.rsbi.databinding.BlogPageBinding
import ro.rsbideveloper.rsbi.databinding.EventsPageRecyclerViewItemBinding

class EventsRecyclerViewAdapter: RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder>() {

    private var dataList = emptyList<Event>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.events_page_recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.itemView.Events_page_recycler_view_item_tv1.text = data.id.toString()
        holder.itemView.Events_page_recycler_view_item_tv2.text = data.published_by
        holder.itemView.Events_page_recycler_view_item_tv3.text = data.image_URL
    }

    fun setData(events: List<Event>) {
        this.dataList = events
        notifyDataSetChanged()  // (*?) but who does this notify ? is it the recycler view that
                                    // is linked to this adapter ? because in regards to the LiveData<>
                                    // that caused this function to be called in the first place, there is
                                    // nothing to notify
    }
}



//class SearchMedicAdapter(
//    private val list: MutableList<SearchMedicData>,
//    private val listener : OnItemClickListener) : RecyclerView.Adapter<SearchMedicAdapter.ViewHolderMedic>() {
//
//    private val TAG : String = "Search Medic Adapter"
//
//    inner class ViewHolderMedic(item_view: View): RecyclerView.ViewHolder(item_view), View.OnClickListener {
//        val searchMedicItem_name = item_view.findViewById<TextView>(R.id.searchMedicItem_name)
//        val searchMedicItem_specialization = item_view.findViewById<TextView>(R.id.searchMedicItem_specialization)
//        val searchMedicItem_city= item_view.findViewById<TextView>(R.id.searchMedicItem_city)
//        val searchMedicItem_address = item_view.findViewById<TextView>(R.id.searchMedicItem_address)
//        //        val searchMedicItem_profile_icon = item_view.findViewById<ImageView>(R.id.searchMedicItem_profile_icon)
//        val searchMedicItem_phone = item_view.findViewById<TextView>(R.id.searchMedicItem_phone)
//
//        init {
//            item_view.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View?) {
//            val medic = list[absoluteAdapterPosition]   // <todo> this or "adapterPosition"
//            if(position != RecyclerView.NO_POSITION)
//                listener.onItemClick(medic)
//            else
//                listener.onItemClick(null)
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(medic : SearchMedicData?)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMedic {
//        val item_view = LayoutInflater.from(parent.context).inflate(R.layout.search_medic_item, parent, false)
//        return ViewHolderMedic(item_view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolderMedic, position: Int) {
//        val current_data = list[position]
//        holder.searchMedicItem_name.text = current_data.name
//        holder.searchMedicItem_specialization.text = current_data.specialization
//        holder.searchMedicItem_city.text = current_data.city
//        holder.searchMedicItem_address.text = current_data.address
//        holder.searchMedicItem_phone.text = current_data.phone
//
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//}