package com.android.completemvvm.di;

import com.android.completemvvm.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface BookShopComponent {
    void inject(MainActivity mainActivity);
}
