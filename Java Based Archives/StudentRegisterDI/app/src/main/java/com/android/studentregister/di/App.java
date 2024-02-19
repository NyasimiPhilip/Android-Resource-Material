package com.android.studentregister.di;

import android.app.Application;

public class App extends Application {
    private static App app;
    private StudentAppComponent studentAppComponent; // Declare as class-level variable

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        // Initialize studentAppComponent here
        studentAppComponent = DaggerStudentAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static App getApp() {
        return app;
    }

    public StudentAppComponent getStudentAppComponent() {
        return studentAppComponent;
    }
}
