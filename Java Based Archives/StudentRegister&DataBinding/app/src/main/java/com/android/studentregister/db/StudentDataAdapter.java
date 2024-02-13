package com.android.studentregister.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.studentregister.R;
import com.android.studentregister.entity.Student;

import java.util.ArrayList;

// Adapter class for RecyclerView to display student data
public class StudentDataAdapter extends RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder> {

    private ArrayList<Student> students;

    // Method to create ViewHolder instances
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the layout for each item in the RecyclerView
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_list_item, viewGroup, false);
        return new StudentViewHolder(itemView);
    }

    // Method to bind data to ViewHolder instances
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        // Retrieve the current student from the list
        Student currentStudent = students.get(i);

        // Set the data to the TextViews in the ViewHolder
        studentViewHolder.name.setText(currentStudent.getName());
        studentViewHolder.email.setText(currentStudent.getEmail());
        studentViewHolder.country.setText(currentStudent.getCountry());
        studentViewHolder.date.setText(currentStudent.getRegisteredTime());
    }

    // Method to determine the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        if (students != null) {
            return students.size();
        } else {
            return 0;
        }
    }

    // Method to update the list of students and notify the adapter of the change
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView name, email, country, date;

        // Constructor to initialize the views
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            email = itemView.findViewById(R.id.tvEmail);
            country = itemView.findViewById(R.id.tvCountry);
            date = itemView.findViewById(R.id.tvTime);
        }
    }
}
