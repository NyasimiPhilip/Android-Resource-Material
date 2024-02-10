package com.example.viewmodelscopedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelscopedemo.adapter.UserAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create an instance of your custom adapter and set it to the RecyclerView
        val adapter = UserAdapter()
        recyclerView.adapter = adapter

        // Call getUserData to initiate data fetching
        mainActivityViewModel.getUserData()

        mainActivityViewModel.users.observe(this, Observer { myUsers ->
            // Update the data in the adapter when LiveData changes
            adapter.setUserList(myUsers)
        })
    }
}


/**mainActivityViewModel.users.observe(this, Observer {myUsers->
myUsers?.forEach {
Log.i("MyTag"," name is ${it.name}")
}

})*/