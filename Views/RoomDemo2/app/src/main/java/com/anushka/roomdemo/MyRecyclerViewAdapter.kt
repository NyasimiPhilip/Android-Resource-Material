// Import necessary libraries and packages
package com.anushka.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.db.Subscriber

// RecyclerView adapter for displaying a list of subscribers
class MyRecyclerViewAdapter(
    private val clickListener: (Subscriber) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    // List to store the subscribers to be displayed
    private val subscribersList = ArrayList<Subscriber>()

    // Inflates the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    // Returns the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return subscribersList.size
    }

    // Binds data to the ViewHolder at the specified position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    // Sets the list of subscribers for the adapter
    fun setList(subscribers: List<Subscriber>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)
    }

}

// ViewHolder class for each item in the RecyclerView
class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    // Binds data to the ViewHolder
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        // Set subscriber data to the corresponding views in the layout
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email

        // Set a click listener to the whole item view
        binding.listItemLayout.setOnClickListener {
            // Invoke the provided click listener when the item is clicked
            clickListener(subscriber)
        }
    }
}
