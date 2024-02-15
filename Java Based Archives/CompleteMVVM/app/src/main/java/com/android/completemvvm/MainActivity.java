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

        observeCategories();
    }

    private void observeCategories() {
        mainActivityViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                for (Category category : categories) {
                    Log.i("MyTag", category.getCategoryName());
                }
                showOnSpinner(categories);
            }
        });
    }

    private void showOnSpinner(List<Category> categories) {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public void loadBookArrayList(int categoryId) {
        mainActivityViewModel.getBooksByCategoryId(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {
                if (books != null) {
                    booksList = new ArrayList<>(books);
                    loadRecyclerView();
                }
            }
        });
    }
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




    public class MainActivityClickHandlers{

        public void onFABClicked(View view){
            //Toast.makeText(getApplicationContext()," FAB Clicked",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,AddnEditActivity.class);
            startActivityForResult(intent,ADD_BOOK_REQUEST_CODE);
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);
            String message = " id is " + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName() + "\n email is " + selectedCategory.getCategoryDescription();
            // Showing selected spinner item
            // Toast.makeText(parent.getContext(), message, Toast.LENGTH_LONG).show();
            loadBookArrayList(selectedCategory.getId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("BookIdTest", " at 4 top id is " + selectedBookId);
        int selectedCategoryId = selectedBookId;
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            Log.i("BookIdTest", " at 4 wrong 2 id is " + selectedBookId);
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddnEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddnEditActivity.UNIT_PRICE));
            mainActivityViewModel.insertBook(book);
        }


        else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == RESULT_OK) {
            Book book = new Book();
            book.setCategoryId(selectedCategoryId);
            book.setBookName(data.getStringExtra(AddnEditActivity.BOOK_NAME));
            book.setUnitPrice(data.getStringExtra(AddnEditActivity.UNIT_PRICE));
            Log.i("BookIdTest", " at 4 id is " + selectedBookId);
            book.setBookId(selectedBookId);
            mainActivityViewModel.updateBook(book);
        }
    }
}


