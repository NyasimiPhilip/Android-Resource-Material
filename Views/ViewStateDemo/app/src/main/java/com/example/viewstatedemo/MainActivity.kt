package com.example.viewstatedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewButton()
    }

    private fun viewButton() {
        val button = findViewById<Button>(R.id.btnCount)
        button.text = "Count is : $count"

        button.setOnClickListener {
            button.text = "Count is : ${++count}"
            Toast.makeText(
                this, "Count is : $count",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}