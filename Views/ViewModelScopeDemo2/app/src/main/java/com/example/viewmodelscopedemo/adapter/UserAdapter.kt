package com.example.viewmodelscopedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelscopedemo.R
import com.example.viewmodelscopedemo.model.User

// com.example.viewmodelscopedemo.Adapter.UserAdapter.kt
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userList: List<User> = emptyList()

    // ViewHolder class to hold the views
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        // Add more TextViews for other user details if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the item layout and return a ViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Bind the data to the views in each item
        val currentUser = userList[position]
        holder.userNameTextView.text = currentUser.name
        // Bind more data if needed
    }

    override fun getItemCount(): Int {
        // Return the number of items in the list
        return userList.size
    }

    // Helper function to update the data set
    fun setUserList(newList: List<User>?) {
        userList = newList ?: emptyList()
        notifyDataSetChanged()
    }
}
