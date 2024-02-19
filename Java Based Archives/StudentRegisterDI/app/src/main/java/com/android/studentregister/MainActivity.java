package com.android.studentregister;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.studentregister.databinding.ActivityMainBinding;
import com.android.studentregister.db.StudentAppDatabase;
import com.android.studentregister.db.StudentDataAdapter;
import com.android.studentregister.di.App;
import com.android.studentregister.entity.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public StudentAppDatabase studentAppDatabase;
    private ArrayList<Student> students;
    private StudentDataAdapter studentDataAdapter;
    public static final int NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1;

    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set up click handlers
        clickHandlers = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandlers(clickHandlers);

        // Set up RecyclerView
        RecyclerView recyclerView = activityMainBinding.include.rvStudents;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Set up data adapter
        studentDataAdapter = new StudentDataAdapter();
        recyclerView.setAdapter(studentDataAdapter);

        App.getApp().getStudentAppComponent().inject(this);



        // Initialize Room database
       /* studentAppDatabase = Room.databaseBuilder(getApplicationContext(), StudentAppDatabase.class, "StudentDB")
                .build();*/

        // Load data from the database
        loadData();

        // Set up swipe-to-delete functionality
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Student studentToDelete = students.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDelete);
            }
        }).attachToRecyclerView(recyclerView);
    }

    // Handle the result of AddNewStudentActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Extract data from the intent
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");
            String country = data.getStringExtra("COUNTRY");

            // Get the current date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
            String currentDate = simpleDateFormat.format(new Date());

            // Create a new student object
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setCountry(country);
            student.setRegisteredTime(currentDate);

            // Add the new student to the database
            addNewStudent(student);
        }
    }

    // Load students data from the database
    private void loadData() {
        new GetAllStudentsAsyncTask().execute();
    }

    // AsyncTask to retrieve all students from the database
    private class GetAllStudentsAsyncTask extends AsyncTask<Void, Void, ArrayList<Student>> {
        @Override
        protected ArrayList<Student> doInBackground(Void... voids) {
            return (ArrayList<Student>) studentAppDatabase.getStudentDao().getAllStudents();
        }

        @Override
        protected void onPostExecute(ArrayList<Student> studentsList) {
            super.onPostExecute(studentsList);
            students = studentsList;
            studentDataAdapter.setStudents(students);
        }
    }

    // Delete a student from the database
    private void deleteStudent(Student student) {
        new DeleteStudentAsyncTask().execute(student);
    }

    // AsyncTask to delete a student from the database
    private class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.getStudentDao().delete(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    // Add a new student to the database
    private void addNewStudent(Student student) {
        new AddNewStudentAsyncTask().execute(student);
    }

    // AsyncTask to add a new student to the database
    private class AddNewStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.getStudentDao().insert(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }
}
