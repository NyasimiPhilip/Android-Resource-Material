package com.android.completemvvm.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.completemvvm.model.Book;
import com.android.completemvvm.model.Category;

// Define the database with entities and version
@Database(entities = {Category.class, Book.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // Define DAO access methods for each entity
    public abstract CategoryDAO categoryDAO();
    public abstract BookDAO bookDAO();

    // Singleton instance of the database
    private static AppDatabase instance;

    // Get an instance of the database
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            // Create the database if it doesn't exist
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    // Callback to insert initial data when the database is created
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Execute the AsyncTask to insert initial data
            new InitialDataAsyncTask(instance).execute();
        }
    };

    // AsyncTask to insert initial data into the database
    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDAO categoryDAO;
        private BookDAO bookDAO;

        // Constructor to initialize DAOs
        public InitialDataAsyncTask(AppDatabase appDatabase) {
            categoryDAO = appDatabase.categoryDAO();
            bookDAO = appDatabase.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Insert initial data into categories and books tables
            insertInitialData();
            return null;
        }

        // Method to insert initial data into categories and books tables
        private void insertInitialData() {
            // Insert categories
            categoryDAO.insert(new Category("Text Books", "Text Books Description"));
            categoryDAO.insert(new Category("Novels", "Novels Description"));
            categoryDAO.insert(new Category("Other Books", "Other Books Description"));

            // Insert books
            bookDAO.insert(new Book("High school Java", "$150", 1));
            bookDAO.insert(new Book("Mathematics for beginners", "$200", 1));
            bookDAO.insert(new Book("Object Oriented Android App Design", "$150", 1));
            bookDAO.insert(new Book("Astrology for beginners", "$190", 1));
            bookDAO.insert(new Book("High school Magic Tricks", "$150", 1));
            bookDAO.insert(new Book("Chemistry for secondary school students", "$250", 1));
            bookDAO.insert(new Book("A Game of Cats", "$19.99", 2));
            bookDAO.insert(new Book("The Hound of the New York", "$16.99", 2));
            bookDAO.insert(new Book("Adventures of Joe Finn", "$13", 2));
            bookDAO.insert(new Book("Arc of witches", "$19.99", 2));
            bookDAO.insert(new Book("Can I run", "$16.99", 2));
            bookDAO.insert(new Book("Story of a joker", "$13", 2));
            bookDAO.insert(new Book("Notes of an alien life cycle researcher", "$1250", 3));
            bookDAO.insert(new Book("Top 9 myths about UFOs", "$789", 3));
            bookDAO.insert(new Book("How to become a millionaire in 24 hours", "$1250", 3));
            bookDAO.insert(new Book("1 hour work month", "$199", 3));
        }
    }
}
