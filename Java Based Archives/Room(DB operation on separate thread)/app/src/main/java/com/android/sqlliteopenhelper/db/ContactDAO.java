package com.android.sqlliteopenhelper.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.sqlliteopenhelper.db.entity.Contact;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public Long addContact(Contact contact);
    @Update
    public void updateContact(Contact contact);
    @Delete
    public void deleteContact(Contact contact);
    @Query("SELECT * FROM contacts")
    public List<Contact> getContacts();
    @Query("SELECT * FROM contacts WHERE contact_id = :contactId")
    public Contact getContact(long contactId);
}
