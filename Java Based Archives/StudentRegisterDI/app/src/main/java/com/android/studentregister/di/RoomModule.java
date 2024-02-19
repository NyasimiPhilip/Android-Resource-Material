package com.android.studentregister.di;

import android.app.Application;

import androidx.room.Room;

import com.android.studentregister.db.StudentAppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    StudentAppDatabase provideStudentAppDatabase(Application application){
        return Room.databaseBuilder(application, StudentAppDatabase.class, "StudentDB").build();
    }
}
