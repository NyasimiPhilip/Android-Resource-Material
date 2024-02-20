package com.android.completemvvm.di;

import android.app.Application;

import com.android.completemvvm.repository.BookRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    BookRepository providesBookRepository(Application application){
        return  new BookRepository(application);
    }
}
