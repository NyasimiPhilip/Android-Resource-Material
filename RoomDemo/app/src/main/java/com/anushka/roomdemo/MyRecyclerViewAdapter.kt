package com.anushka.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.db.Subscriber

// Adapter class for RecyclerView that displays a list of Subscribers
class MyRecyclerViewAdapter(
    private val subscribersList: List<Subscriber>, // List of Subscriber objects to be displayed
    private val clickListener: (Subscriber) -> Unit // Listener to handle item clicks
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each list item using DataBindingUtil
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    // Returns the total number of items in the data set held by the adapter
    override fun getItemCount(): Int {
        return subscribersList.size
    }

    // Called to display the data at a specified position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Get the data at the specified position
        val subscriber = subscribersList[position]
        // Bind the data to the ViewHolder
        holder.bind(subscriber, clickListener)
    }

    // ViewHolder class that represents each item in the RecyclerView
    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // Bind data to the ViewHolder
        fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
            // Set the name and email text in the layout
            binding.nameTextView.text = subscriber.name
            binding.emailTextView.text = subscriber.email
            // Set a click listener for the entire list item
            binding.listItemLayout.setOnClickListener {
                // Invoke the clickListener when the item is clicked, passing the associated Subscriber
                clickListener(subscriber)
            }
        }
    }
}
