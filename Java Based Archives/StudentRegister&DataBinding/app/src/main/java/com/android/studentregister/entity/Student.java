package com.android.studentregister.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.android.studentregister.BR;

@Entity(tableName = "student_table")
public class Student extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String name;
    private String email;
    private String country;
    private String registeredTime;

    // Default constructor, marked with @Ignore annotation to avoid room conflicts
    @Ignore
    public Student() {
    }

    // Parameterized constructor to initialize fields
    public Student(int studentId, String name, String email, String country, String registeredTime) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.country = country;
        this.registeredTime = registeredTime;
    }

    // Getters and Setters
    @Bindable
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }

    @Bindable
    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
        notifyPropertyChanged(BR.registeredTime);
    }
}
