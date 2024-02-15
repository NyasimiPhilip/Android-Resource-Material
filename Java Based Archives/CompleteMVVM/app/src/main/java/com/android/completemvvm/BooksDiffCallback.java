package com.android.completemvvm;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.android.completemvvm.model.Book;

import java.util.ArrayList;

// This class calculates the difference between two lists of books
public class BooksDiffCallback extends DiffUtil.Callback {

    // Lists of old and new books
    ArrayList<Book> oldBooksList;
    ArrayList<Book> newBooksList;

    // Constructor to initialize the old and new books lists
    public BooksDiffCallback(ArrayList<Book> oldBooksList, ArrayList<Book> newBooksList) {
        this.oldBooksList = oldBooksList;
        this.newBooksList = newBooksList;
    }

    // Returns the size of the old list
    @Override
    public int getOldListSize() {
        return oldBooksList == null ? 0 : oldBooksList.size();
    }

    // Returns the size of the new list
    @Override
    public int getNewListSize() {
        return newBooksList == null ? 0 : newBooksList.size();
    }

    // Checks if the items in the old and new lists are the same
    @Override
    public boolean areItemsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBooksList.get(oldBookPosition).getBookId() == newBooksList.get(newBookPosition).getBookId();
    }

    // Checks if the contents of the items in the old and new lists are the same
    @Override
    public boolean areContentsTheSame(int oldBookPosition, int newBookPosition) {
        return oldBooksList.get(oldBookPosition).equals(newBooksList.get(newBookPosition));
    }

    // Returns any payload changes between the items in the old and new lists
    @Nullable
    @Override
    public Object getChangePayload(int oldBookPosition, int newBookPosition) {
        return super.getChangePayload(oldBookPosition, newBookPosition);
    }
}
