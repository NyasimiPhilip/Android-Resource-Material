package com.android.completemvvm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.completemvvm.adapter.BookAdapter;
import com.android.completemvvm.databinding.ActivityMainBinding;
import com.android.completemvvm.model.Book;
import com.android.completemvvm.model.Category;
import com.android.completemvvm.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private ArrayList<Book> booksList;
    private BookAdapter booksAdapter;
    private RecyclerView booksRecyclerView;
    private int selectedBookId;
    private ArrayList<Category> categoriesList;
    private Category selectedCategory;
    public static final int ADD_BOOK_REQUEST_CODE = 1;
    public static final int EDIT_BOOK_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(activityMainBinding.toolbar);

        // Initialize ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize Click Handlers
        MainActivityClickHandlers mainActivityClickHandlers = new MainActivityClickHandlers();

        // Set Click Handlers
        activityMainBinding.setClickHandlers(mainActivityClickHandlers);
        activityMainBinding.setLifecycleOwner(this);

        // Observe categories
        observeCategories();
    }

    // Observe categories changes
    private void observeCategories() {
        mainActivityViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                // Log the categories
                for (Category category : categories) {
                    Log.i("MyTag", category.getCategoryName());
                }
                // Show categories on spinner
                showOnSpinner(categories);
            }
        });
    }

    // Show categories on spinner
    private void showOnSpinner(List<Category> categories) {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    // Load books list based on category
    public void loadBookArrayList(int categoryId) {
        mainActivityViewModel.getBooksByCategoryId(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                if (books != null) {
                    // Copy books to list
                    booksList = new ArrayList<>(books);
                    // Load RecyclerView
                    loadRecyclerView();
                }
            }
        });
    }

    // Load RecyclerView with books
    private void loadRecyclerView() {
        // Initialize RecyclerView
        booksRecyclerView = activityMainBinding.secondaryLayout.rvBooks;
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        booksRecyclerView.setHasFixedSize(true);

        // Initialize adapter with click listener
        booksAdapter = new BookAdapter(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                selectedBookId = book.getBookId();
                Log.i("BookIdTest", " at 1 id is " + selectedBookId);
                Intent intent = new Intent(MainActivity.this, AddnEditActivity.class);
                intent.putExtra(AddnEditActivity.BOOK_ID, selectedBookId);
                Log.i("BookIdTest", " at 2 id is " + selectedBookId);
                intent.putExtra(AddnEditActivity.BOOK_NAME, book.getBookName());
                intent.putExtra(AddnEditActivity.UNIT_PRICE, book.getUnitPrice());
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE);
            }
        });

        // Set adapter to RecyclerView
        booksRecyclerView.setAdapter(booksAdapter);

        // Set the list of books to the adapter
        if (booksList != null) {
            booksAdapter.setBooks(booksList);
        }

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Book bookToDelete = booksList.get(viewHolder.getAdapterPosition());
                mainActivityViewModel.deleteBook(bookToDelete);
            }
        }).attachToRecyclerView(booksRecyclerView);
    }

    // Click handlers for main activity
    public class MainActivityClickHandlers{

        // FAB click handler
        public void onFABClicked(View view){
            Intent intent=new Intent(MainActivity.this,AddnEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);
        }

        // Spinner item selection handler
        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            // Log the selected category details
            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();
            // Load books list based on selected category
            loadBookArrayList(selectedCategory.getId());
        }
    }

    // Inflate the menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Handle activity result from AddnEditActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Log the selected book id
        Log.i("BookIdTest", " at 4 top id is " + selectedBookId);
        int selectedCategoryId = selectedBookId;
        // If adding a new book
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            // Create a new book and insert it
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddnEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddnEditActivity.UNIT_PRICE));
            mainActivityViewModel.insertBook(book);
        }
        // If editing an existing book
        else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            // Create a new book and update it
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddnEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddnEditActivity.UNIT_PRICE));
            book.setBookId(selectedBookId);
            mainActivityViewModel.updateBook(book);
        }
    }
}
