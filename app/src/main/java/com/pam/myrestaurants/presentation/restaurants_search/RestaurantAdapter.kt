package com.pam.myrestaurants.presentation.restaurants_search


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pam.myrestaurants.databinding.ViewHolderSearchListBinding
import com.pam.myrestaurants.domain.model.DRestaurant
import java.util.*


class RestaurantAdapter : PagingDataAdapter<DRestaurant,RestaurantAdapter.RestaurantViewHolder>(COMPARATOR),
    Filterable {

    private var listener :((DRestaurant)->Unit)?=null
    var list = ArrayList<DRestaurant>()
    var backup = ArrayList<DRestaurant>()
    fun setContentList(data: ArrayList<DRestaurant>) {
        this.list.addAll(data)
        this.backup.addAll(data)
        notifyDataSetChanged()
    }

    class RestaurantViewHolder(val viewHolder: ViewHolderSearchListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ViewHolderSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.viewHolder.restaurant = list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun itemClickListener(l:(DRestaurant)->Unit){
        listener= l
    }

    override fun getFilter(): Filter {
        return filter;
    }

    /**
     * For Filter I have used Regular expression but it can be improved by Using Trie Data Structure
     */
    private val filter = object : Filter(){
        override fun performFiltering(keyword: CharSequence): FilterResults {
            val filtereddata: ArrayList<DRestaurant> = ArrayList<DRestaurant>()

            if (keyword.toString().isBlank()) filtereddata.addAll(backup.toList())
            else {
                for (obj in backup) {
                    if (obj.name.lowercase().contains(keyword.toString().lowercase()) ||
                        obj.cuisineType.lowercase().contains(keyword.toString().lowercase()) ||
                        obj.menuItems.lowercase().contains(keyword.toString().lowercase())
                    ) filtereddata.add(obj)
                }
            }
            val results = FilterResults()
            results.values = filtereddata
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults) {
            list.clear()
            list.addAll(p1.values as ArrayList<DRestaurant>)
            notifyDataSetChanged()
        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<DRestaurant>() {
            override fun areItemsTheSame(oldItem: DRestaurant, newItem: DRestaurant): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DRestaurant, newItem: DRestaurant): Boolean {
                return oldItem == newItem
            }
        }
    }
}