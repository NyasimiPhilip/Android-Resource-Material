// Define the package name for the application
package com.anushka.notificationdemo

// Import necessary Android and Kotlin libraries
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

// MainActivity class, extending AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Unique ID for the notification channel
    private val channelID = "com.anushka.notificationdemo.channel1"

    // NotificationManager instance to handle notifications
    private var notificationManager: NotificationManager? = null

    private val KEY_REPLY = "key_reply"

    // Override the onCreate method, called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view for the activity from layout file (activity_main.xml)
        setContentView(R.layout.activity_main)

        // Find the Button with ID 'button' from the layout
        val button = findViewById<Button>(R.id.button)

        // Get the NotificationManager service
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel with specified ID, name, and description
        createNotificationChannel(channelID, "DemoChannel", "This is a demo")

        // Set a click listener for the button to display a notification when clicked
        button.setOnClickListener {
            displayNotification()
        }
    }

    // Function to display a notification
    private fun displayNotification() {
        // Unique ID for the notification
        val notificationId = 45

        // Create an Intent to launch the SecondActivity when the notification is tapped
        val tapResultIntent = Intent(this, SecondActivity::class.java)

        // Create a PendingIntent for the notification
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Create RemoteInput for reply action
        val remoteInput: RemoteInput = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Insert your name here")
                .build()
        }

        // Create reply action for the notification
        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
            0,
            "REPLY",
            pendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        // Create Intents and PendingIntents for additional actions in the notification
        val intent2 = Intent(this, DetailsActivity::class.java)
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2: NotificationCompat.Action =
            NotificationCompat.Action.Builder(0, "Details", pendingIntent2).build()

        val intent3 = Intent(this, SettingsActivity::class.java)
        val pendingIntent3: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action3: NotificationCompat.Action =
            NotificationCompat.Action.Builder(0, "Settings", pendingIntent3).build()

        // Build a notification using NotificationCompat.Builder
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true) // Remove the notification when clicked
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //setContentIntent(pendingIntent) would have set the main action for the notification, launching the SecondActivity,
            .addAction(replyAction) // Add reply action
            .addAction(action2)    // Add an additional action for "Details"
            .addAction(action3)    // Add an additional action for "Settings"
            .build()

        // Notify the NotificationManager to display the notification
        notificationManager?.notify(notificationId, notification)
    }

    // Function to create a notification channel (required for Android Oreo and above)
    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        // Check if the Android version is Oreo or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Set the importance level for the channel
            val importance = NotificationManager.IMPORTANCE_HIGH

            // Create a new NotificationChannel with specified ID, name, and importance
            val channel = NotificationChannel(id, name, importance).apply {
                // Set the description for the channel
                description = channelDescription
            }

            // Register the channel with the NotificationManager
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
