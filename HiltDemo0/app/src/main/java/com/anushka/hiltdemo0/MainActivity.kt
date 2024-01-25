package com.anushka.hiltdemo0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint // Import the Hilt AndroidEntryPoint annotation
import javax.inject.Inject // Import the Inject annotation

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataSource: DataSource // Use Dagger's @Inject annotation to let Dagger know that it should provide an instance of DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the content view for the activity using the layout resource 'activity_main'

        dataSource.getRemoteData() // Call a method from the injected DataSource instance to get remote data
    }
}
