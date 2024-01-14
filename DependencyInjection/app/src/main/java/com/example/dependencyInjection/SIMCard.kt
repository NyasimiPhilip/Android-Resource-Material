package com.example.dependencyInjection

import android.util.Log
import com.example.dependencyInjection.ServiceProvider
import javax.inject.Inject


class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {

    //private lateinit var serviceProvider: ServiceProvider uncomment this to use method injection

    init {
        Log.i("MYTAG","SIM Card Constructed")
    }
    /**fun setServiceProvider(serviceProvider: ServiceProvider){
        this.serviceProvider = serviceProvider
    }//Method Injection*/

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}