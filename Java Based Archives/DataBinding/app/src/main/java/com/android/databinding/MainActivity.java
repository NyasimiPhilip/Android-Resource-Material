package com.android.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.android.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  /*   private TextView nameText;
    private TextView emailText;
  */
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nameText=findViewById(R.id.tvStudentName);
        //emailText=findViewById(R.id.tvStudentEmail);

      /*  nameText.setText(getCurrentStudent().getStudentName());
        emailText.setText(getCurrentStudent().getStudentEmail());
       */
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setStudent(getCurrentStudent());


    }

    private Student getCurrentStudent(){

        Student student = new Student();
        student.setStudentName("Alex");
        student.setStudentEmail("alex@gmail.com");
        return student;

    }
}