package com.anushka.hiltdemo0

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// The @HiltAndroidApp annotation is used to enable Hilt's functionality in the Application class
@HiltAndroidApp
class App : Application()
