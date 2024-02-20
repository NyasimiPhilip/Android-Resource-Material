package com.android.completemvvm.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.completemvvm.repository.BookRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
public class MainActivityViewModelFactory  implements ViewModelProvider.Factory {

    private final BookRepository bookRepository;

    @Inject

    public MainActivityViewModelFactory(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(bookRepository);
    }
}

