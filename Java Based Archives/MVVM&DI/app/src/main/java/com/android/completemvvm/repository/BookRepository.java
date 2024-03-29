package com.android.completemvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.completemvvm.db.AppDatabase;
import com.android.completemvvm.db.BookDAO;
import com.android.completemvvm.db.CategoryDAO;
import com.android.completemvvm.model.Book;
import com.android.completemvvm.model.Category;

import java.util.List;

public class BookRepository {
    // DAOs and LiveData for Categories and Books
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;

    // Constructor initializing database and DAOs
    public BookRepository(Application application) {
        AppDatabase booksDatabase = AppDatabase.getInstance(application);
        categoryDAO = booksDatabase.categoryDAO();
        bookDAO = booksDatabase.bookDAO();
        categories = categoryDAO.getAllCategories(); // Retrieve all categories
        books = bookDAO.getAllBooks(); // Retrieve all books
    }

    // Getters for LiveData of Categories and Books this do not async tasks because livedata
    // is inherently asynchronous
    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    // Get books by category ID
    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDAO.getBooksByCategoryId(categoryId);
    }

    // Methods for inserting, deleting, and updating Categories
    public void insertCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void deleteCategory(Category category) {
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void updateCategory(Category category) {
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    // Methods for inserting, deleting, and updating Books
    public void insertBook(Book book) {
        new InsertBookAsyncTask(bookDAO).execute(book);
    }

    public void deleteBook(Book book) {
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    public void updateBook(Book book) {
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }

    // AsyncTask for inserting a Category
    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories[0]); // Insert category in background
            return null;
        }
    }

    // AsyncTask for deleting a Category
    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.delete(categories[0]); // Delete category in background
            return null;
        }
    }

    // AsyncTask for updating a Category
    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDAO categoryDAO;

        UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.update(categories[0]); // Update category in background
            return null;
        }
    }

    // AsyncTask for inserting a Book
    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books[0]); // Insert book in background
            return null;
        }
    }

    // AsyncTask for deleting a Book
    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        DeleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.delete(books[0]); // Delete book in background
            return null;
        }
    }

    // AsyncTask for updating a Book
    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        UpdateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.update(books[0]); // Update book in background
            return null;
        }
    }
}
