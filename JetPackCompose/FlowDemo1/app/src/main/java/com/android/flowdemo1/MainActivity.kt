package com.android.flowdemo1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myFlow = flow<Int>{
            for (i in 1..100){// In the context of flows this is the producer
                emit(i)
                delay(1000L)
            }

        }
        val textView = findViewById<TextView>(R.id.tvResult)
        CoroutineScope(Dispatchers.Main).launch{
            myFlow.collectLatest {
                textView.text = "Current Index is $it"
            }
        }
    }
}


