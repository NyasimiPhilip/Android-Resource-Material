package com.example.dependencyInjection

import android.app.Application

class SmartPhoneApplication: Application() {
    lateinit var smartPhoneFunctionComponent: smartphoneFunction
    override fun onCreate(){
        super.onCreate()
        smartPhoneFunctionComponent = initDagger()


    }
    private fun initDagger(): smartphoneFunction =
        DaggersmartphoneFunction
            .builder()
            .memoryCardModule(MemoryCardModule(100))
            .build()



}