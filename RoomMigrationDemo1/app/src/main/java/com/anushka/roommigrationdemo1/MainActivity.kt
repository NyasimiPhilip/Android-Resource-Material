package com.anushka.roommigrationdemo1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get an instance of the Data Access Object (DAO) from the Room Database
        val dao = StudentDatabase.getInstance(application).subscriberDAO

        // Find views by their IDs
        val nameEditText = findViewById<EditText>(R.id.etName)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val button = findViewById<Button>(R.id.btnSubmit)

        // Set a click listener for the submit button
        button.setOnClickListener {
            // Use lifecycleScope to launch a coroutine
            lifecycleScope.launch {
                // Get the name and email values from the EditText fields
                nameEditText.text.let {
                    // Insert a new Student into the Room Database using the DAO
                    dao.insertStudent(Student(0, it.toString(), emailEditText.text.toString()))

                    // Clear the EditText fields after inserting the student
                    nameEditText.setText("")
                    emailEditText.setText("")
                }
            }
        }
    }
}
