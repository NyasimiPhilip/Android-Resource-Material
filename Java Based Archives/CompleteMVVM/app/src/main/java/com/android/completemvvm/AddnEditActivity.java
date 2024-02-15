package com.android.completemvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.completemvvm.databinding.ActivityAddAndEditBinding;
import com.android.completemvvm.model.Book;

public class AddnEditActivity extends AppCompatActivity {
    private Book book;
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_NAME = "bookName";
    public static final String UNIT_PRICE = "unitPrice";
    private ActivityAddAndEditBinding activityAddAndEditBinding;
    private AddnEditActivityClickHandlers addnEditActivityClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        book = new Book();
        activityAddAndEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_and_edit);
        activityAddAndEditBinding.setBook(book);

        addnEditActivityClickHandlers = new AddnEditActivityClickHandlers(this);
        activityAddAndEditBinding.setClickHandler(addnEditActivityClickHandlers);

        Intent intent = getIntent();
        if (intent.hasExtra(BOOK_ID)) {
            Log.i("BookIdTest", " at 3 id is " + intent.getIntExtra(BOOK_ID, 0));
            setTitle("Edit Book");
            book.setBookName(intent.getStringExtra(BOOK_NAME));
            book.setUnitPrice(intent.getStringExtra(UNIT_PRICE));

        } else {
            setTitle("Add New Book");

        }
    }

    public class AddnEditActivityClickHandlers {
        private Context context;


        public AddnEditActivityClickHandlers(Context context) {
            this.context = context;

        }

        public void onSubmitButtonClicked(View view) {
            if (book.getBookName() == null) {
                Toast.makeText(context, "Name field cannot be empty", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra(BOOK_NAME, book.getBookName());
                intent.putExtra(UNIT_PRICE, book.getUnitPrice());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
