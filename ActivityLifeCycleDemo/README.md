## Android Activity Lifecycle Demonstration</font>

This is code for an Android application that demonstrates the lifecycle of activities in Android, specifically focusing on the MainActivity and SecondActivity.

### MainActivity:</font>

- When the activity is created (<code>onCreate()</code>), it sets the layout from <code>activity_main.xml</code>.
- It initializes various UI components like TextView, EditText, and Button.
- The user enters their name in the EditText field and clicks the "Submit" button.
- If the name field is empty, it displays a toast message asking the user to enter their name.
- If a name is entered, it displays a welcome message along with the entered name in the TextView and makes the "Offers" button visible.
- Clicking the "Offers" button opens the SecondActivity, passing the entered name as an extra with the intent.
- It also logs various lifecycle events (<code>onStart()</code>, <code>onResume()</code>, <code>onPause()</code>, <code>onStop()</code>, <code>onDestroy()</code>, <code>onRestart()</code>).

<h2>SecondActivity</h2>

- When the activity is created (<code>onCreate()</code>), it sets the layout from <code>activity_second.xml</code>.
- It retrieves the username passed from the MainActivity using the intent.
- It displays a message in a TextView welcoming the user and mentioning an offer (in this case, free access to content for one month).
- It also logs various lifecycle events (<code>onStart()</code>, <code>onResume()</code>, <code>onPause()</code>, <code>onStop()</code>, <code>onDestroy()</code>, <code>onRestart()</code>).

Overall, this application demonstrates the flow of activity lifecycle methods in Android and how to pass data between activities using intents.
