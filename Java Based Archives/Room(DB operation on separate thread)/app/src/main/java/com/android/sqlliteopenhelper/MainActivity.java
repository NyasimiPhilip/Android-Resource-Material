package com.android.sqlliteopenhelper;


import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.sqlliteopenhelper.adapter.ContactsAdapter;
import com.android.sqlliteopenhelper.db.ContactAppDatabase;
import com.android.sqlliteopenhelper.db.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ContactsAdapter contactsAdapter;
    private final ArrayList<Contact> contactArrayList = new ArrayList<>();
    private ContactAppDatabase contactAppDatabase;
    private ExecutorService executorService; // ExecutorService for managing background tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Contacts Manager");

        // Initializing RecyclerView and ContactAppDatabase
        RecyclerView recyclerView = findViewById(R.id.recycler_view_contacts);
        contactAppDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ContactAppDatabase.class,
                "ContactDB"
        )
                .addCallback(callback)
                .build();

        // Initialize ExecutorService with a fixed thread pool
        executorService = Executors.newFixedThreadPool(2); // Two threads for background tasks

        // Retrieving contacts from database
        // Execute database query asynchronously using ExecutorService
        executorService.execute(() -> {
            contactArrayList.addAll(contactAppDatabase.getContactDAO().getContacts());
            // Update UI on the main thread after database query completes
            runOnUiThread(() -> {
                contactsAdapter.notifyDataSetChanged();
            });
        });

        // Initializing ContactsAdapter and setting up RecyclerView
        contactsAdapter = new ContactsAdapter(this, contactArrayList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);

        // Setting up FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call addAndEditContacts method to add a new contact
                addAndEditContacts(false, null, -1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Method to delete a contact
    private void deleteContact(Contact contact, int position) {
        executorService.execute(() -> {
            contactAppDatabase.getContactDAO().deleteContact(contact);
            contactArrayList.remove(position);
            // Update UI on the main thread after deletion
            runOnUiThread(() -> {
                contactsAdapter.notifyDataSetChanged();
            });
        });
    }

    // Method to update a contact
    public void addAndEditContacts(final boolean isUpdate, final Contact contact, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.layout_add_contact, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText newContact = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        contactTitle.setText(!isUpdate ? "Add New Contact" : "Edit Contact");

        if (isUpdate && contact != null) {
            newContact.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // Empty method body, handled later in onClickListener of the dialog's positive button
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if (isUpdate) {
                            deleteContact(contact, position);
                        } else {
                            dialogBox.cancel();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(newContact.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter contact name!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                if (isUpdate && contact != null) {
                    contact.setName(newContact.getText().toString());
                    contact.setEmail(contactEmail.getText().toString());
                    updateContact(newContact.getText().toString(), contactEmail.getText().toString(), position);
                } else {
                    createContact(newContact.getText().toString(), contactEmail.getText().toString());
                }
            }
        });
    }

    // Method to update a contact
    private void updateContact(String name, String email, int position) {
        Contact contact = contactArrayList.get(position);
        contact.setName(name);
        contact.setEmail(email);
        executorService.execute(() -> {
            contactAppDatabase.getContactDAO().updateContact(contact);
            contactArrayList.set(position, contact);
            // Update UI on the main thread after update
            runOnUiThread(() -> {
                contactsAdapter.notifyDataSetChanged();
            });
        });
    }

    // Method to create a new contact
    private void createContact(String name, String email) {
        executorService.execute(() -> {
            Contact contact = new Contact();
            contact.setName(name);
            contact.setEmail(email);
            long id = contactAppDatabase.getContactDAO().addContact(contact);
            contact.setId(id);
            contactArrayList.add(0, contact);
            // Update UI on the main thread after creation
            runOnUiThread(() -> {
                contactsAdapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown ExecutorService when activity is destroyed
        executorService.shutdown();
    }
    RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.i("MainActivity", "callback on create invoked");
            createContact("name 1", "email 1");
            createContact("name 2", "email 2");
            createContact("name 3", "email 3");
            createContact("name 4", "email 4");

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.i("MainActivity", "callback on create invoked");
        }
    };
}
