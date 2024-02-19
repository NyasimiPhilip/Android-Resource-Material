package com.android.studentregister.db;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.studentregister.databinding.StudentListItemBinding;
import com.android.studentregister.entity.Student;

import java.util.ArrayList;

// Adapter class for RecyclerView to display student data
public class StudentDataAdapter extends RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder> {

    private ArrayList<Student> students;

    // Method to create ViewHolder instances
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView using data binding
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StudentListItemBinding itemBinding = StudentListItemBinding.inflate(layoutInflater, parent, false);
        return new StudentViewHolder(itemBinding);
    }

    // Method to bind data to ViewHolder instances
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Retrieve the current student from the list
        Student currentStudent = students.get(position);

        // Bind the student object to the layout using data binding
        holder.bind(currentStudent);
    }

    // Method to determine the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    // Method to update the list of students and notify the adapter of the change
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private final StudentListItemBinding binding;

        // Constructor to initialize the views using data binding
        public StudentViewHolder(@NonNull StudentListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        // Method to bind the student object to the layout using data binding
        public void bind(Student student) {
            binding.setStudent(student);
            // This is necessary to force the data binding to execute immediately,
            // otherwise, it would wait until the next layout pass to update the UI
            binding.executePendingBindings();
        }
    }
}
