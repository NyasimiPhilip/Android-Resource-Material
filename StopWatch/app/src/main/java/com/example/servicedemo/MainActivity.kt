package com.example.servicedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isStarted = false
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startOrStop()
        }
        binding.btnReset.setOnClickListener {
            reset()
        }

        serviceIntent = Intent(applicationContext, StopWatchService::class.java)

    }
    private fun startOrStop(){
        if(isStarted){
            stop()
        }else{
            start()
        }
    }

    private fun start() {
        binding.btnStart.text = "Stop"
        isStarted = true
    }
    private fun stop(){
        binding.btnStart.text = "Start"
        isStarted =  false

    }
    private fun reset(){

    }



}
