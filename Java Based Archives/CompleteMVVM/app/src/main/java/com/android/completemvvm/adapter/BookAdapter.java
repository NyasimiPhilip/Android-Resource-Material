package com.android.completemvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.completemvvm.R;
import com.android.completemvvm.databinding.BookListItemBinding;
import com.android.completemvvm.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<Book> books;
    private OnItemClickListener listener;

    // Constructor to initialize the adapter with a click listener
    public BookAdapter(OnItemClickListener listener) {
        this.listener = listener;
        this.books = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout using Data Binding
        BookListItemBinding bookListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_list_item, parent, false);
        return new BookViewHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Get the book at the specified position and bind it to the ViewHolder
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return books.size();
    }

    // Method to set the list of books and update the UI
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    // ViewHolder class
    class BookViewHolder extends RecyclerView.ViewHolder {
        private BookListItemBinding bookListItemBinding;

        public BookViewHolder(@NonNull BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding = bookListItemBinding;

            // Set click listener on the root view of the item layout
            bookListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    if (listener != null && clickedPosition != RecyclerView.NO_POSITION) {
                        // Trigger onItemClick event if listener is not null and position is valid
                        listener.onItemClick(books.get(clickedPosition));
                    }
                }
            });
        }

        // Method to bind data to the ViewHolder
        public void bind(Book book) {
            // Bind the book object to the layout using data binding
            bookListItemBinding.setBook(book);
            // Execute any pending bindings to update the UI
            bookListItemBinding.executePendingBindings();
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(Book book);}

}

