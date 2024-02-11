package com.android.sqlliteopenhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.sqlliteopenhelper.MainActivity;
import com.android.sqlliteopenhelper.R;
import com.android.sqlliteopenhelper.db.entity.Contact;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Contact> contactssList;
    private MainActivity mainActivity;

    // ViewHolder class to hold references to views
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView emil;

        public MyViewHolder(View view) {
            super(view);
            // Initialize TextViews
            name = view.findViewById(R.id.name);
            emil = view.findViewById(R.id.email);
        }
    }

    // Constructor to initialize adapter with data and main activity reference
    public ContactsAdapter(Context context, ArrayList<Contact> contacts, MainActivity mainActivity) {
        this.context = context;
        this.contactssList = contacts;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for a single list item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        // Create and return a new ViewHolder
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Get the data model based on position
        final Contact contact = contactssList.get(position);

        // Set item views based on your views and data model
        holder.name.setText(contact.getName());
        holder.emil.setText(contact.getEmail());

        // Set click listener for item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call addAndEditContacts method in MainActivity when item clicked
                mainActivity.addAndEditContacts(true, contact, position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contactssList.size();
    }
}
