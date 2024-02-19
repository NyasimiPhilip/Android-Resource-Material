package com.android.studentregister.di;

import com.android.studentregister.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, RoomModule.class})
@Singleton
public interface StudentAppComponent {
    void inject(MainActivity mainActivity);

}
