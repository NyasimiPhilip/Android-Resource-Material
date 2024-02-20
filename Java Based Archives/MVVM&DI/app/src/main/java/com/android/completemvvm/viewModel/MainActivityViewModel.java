package com.android.completemvvm.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.completemvvm.model.Book;
import com.android.completemvvm.model.Category;
import com.android.completemvvm.repository.BookRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private BookRepository bookRepository; // Repository for accessing data

    // Constructor receiving the Application object
    public MainActivityViewModel(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    // Method to get LiveData of categories
    public LiveData<List<Category>> getCategories() {
        return bookRepository.getCategories();
    }

    // Method to get LiveData of all books
    public LiveData<List<Book>> getAllBooks() {
        return bookRepository.getBooks();
    }

    // Method to get LiveData of books by category ID
    public LiveData<List<Book>> getBooksByCategoryId(int categoryId) {
        return bookRepository.getBooks(categoryId);
    }

    // Method to insert a new category
    public void insertCategory(Category category) {
        bookRepository.insertCategory(category);
    }

    // Method to delete a category
    public void deleteCategory(Category category) {
        bookRepository.deleteCategory(category);
    }

    // Method to update a category
    public void updateCategory(Category category) {
        bookRepository.updateCategory(category);
    }

    // Method to insert a new book
    public void insertBook(Book book) {
        bookRepository.insertBook(book);
    }

    // Method to delete a book
    public void deleteBook(Book book) {
        bookRepository.deleteBook(book);
    }

    // Method to update a book
    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }
}
