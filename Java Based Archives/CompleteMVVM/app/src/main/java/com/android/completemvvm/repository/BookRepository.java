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
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>>categories;
    private LiveData<List<Book>>books;

    public BookRepository(Application application){

        AppDatabase booksDatabase =  AppDatabase.getInstance(application);
        categoryDAO = booksDatabase.categoryDAO();
        bookDAO = booksDatabase.bookDAO();
    }
    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks(int categoryId) {
        return bookDAO.getBooksByCategoryId(categoryId);
    }

    private static class InsetCategoryAsyncTask extends AsyncTask<Category, Void, Void>{

        @Override
        protected Void doInBackground(Category... categories) {
            return null;
        }
    }
}
