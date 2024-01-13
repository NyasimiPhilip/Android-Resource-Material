package com.example.dependencyInjection

import android.util.Log
import com.example.dependencyInjection.ServiceProvider

class SIMCard(private  val serviceProvider: ServiceProvider) {


    init {
        Log.i("MYTAG","SIM Card Constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}