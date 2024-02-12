package com.android.twowaydatabinding.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.twowaydatabinding.BR;

public class Contact extends BaseObservable {
    private int id;
    private String name;
    private String email;

    public Contact() {
        // Default constructor
    }

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); // Notify listeners of property change
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email); // Notify listeners of property change
    }

    // Optional: Add method for updating both name and email at once
    public void updateContact(String name, String email) {
        this.name = name;
        this.email = email;
        notifyPropertyChanged(BR.name); // Notify listeners of property change
        notifyPropertyChanged(BR.email); // Notify listeners of property change
    }
}
