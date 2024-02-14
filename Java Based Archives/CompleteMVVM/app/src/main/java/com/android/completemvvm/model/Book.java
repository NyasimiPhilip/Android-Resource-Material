package com.android.completemvvm.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "books_table",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("category_id")}) // Create an index for the category_id column
public class Book extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int bookId;

    @ColumnInfo(name= "book_name")
    private String bookName;

    @ColumnInfo(name= "unit_price")
    private String unitPrice;

    @ColumnInfo(name= "category_id")
    private int categoryId;

    // Parameterized constructor
    public Book(String bookName, String unitPrice, int categoryId){
        this.bookName = bookName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }

    // Default constructor annotated with @Ignore
    @Ignore
    public Book() {
        // This constructor is ignored by Room
    }

    @Bindable
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
       notifyPropertyChanged(BR.bookId);
    }

    @Bindable
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        notifyPropertyChanged(BR.bookName);
    }

    @Bindable
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        notifyPropertyChanged(BR.unitPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
       notifyPropertyChanged(BR.categoryId);
    }
}
