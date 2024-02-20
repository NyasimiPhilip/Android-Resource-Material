package com.android.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivityViewModelFactory implements ViewModelProvider.Factory {
    private int initialValue;

    public MainActivityViewModelFactory(int initialValue) {
        this.initialValue = initialValue;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(initialValue);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
