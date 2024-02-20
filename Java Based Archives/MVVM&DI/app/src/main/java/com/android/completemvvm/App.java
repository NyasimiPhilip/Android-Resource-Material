package com.android.completemvvm;

import android.app.Application;

import com.android.completemvvm.di.AppModule;
import com.android.completemvvm.di.BookShopComponent;
import com.android.completemvvm.di.DaggerBookShopComponent;

public class App  extends Application {
    private static App app;
    private BookShopComponent bookShopComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        app = this;
        bookShopComponent = DaggerBookShopComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public BookShopComponent getBookShopComponent() {
        return bookShopComponent;
    }

    public static App getApp() {
        return app;
    }
}
