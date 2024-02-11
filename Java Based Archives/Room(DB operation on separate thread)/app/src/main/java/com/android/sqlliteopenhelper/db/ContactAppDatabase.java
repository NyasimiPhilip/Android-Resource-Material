package com.android.sqlliteopenhelper.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.sqlliteopenhelper.db.entity.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();
}
