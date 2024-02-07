<h2>Android Activity Lifecycle Demonstration</h2>
<p>This is code for an Android application that demonstrates the lifecycle of activities in Android, specifically focusing on the MainActivity and SecondActivity</p>

<h3>MainActivity:</h3>

<ul>
  <li>When the activity is created (<code>onCreate()</code>), it sets the layout from <code>activity_main.xml</code>.</li>
  <li>It initializes various UI components like TextView, EditText, and Button.</li>
  <li>The user enters their name in the EditText field and clicks the "Submit" button.</li>
  <li>If the name field is empty, it displays a toast message asking the user to enter their name.</li>
  <li>If a name is entered, it displays a welcome message along with the entered name in the TextView and makes the "Offers" button visible.</li>
  <li>Clicking the "Offers" button opens the SecondActivity, passing the entered name as an extra with the intent.</li>
  <li>It also logs various lifecycle events (<code>onStart()</code>, <code>onResume()</code>, <code>onPause()</code>, <code>onStop()</code>, <code>onDestroy()</code>, <code>onRestart()</code>).</li>
</ul>

<h3>SecondActivity:</h3>

<ul>
  <li>When the activity is created (<code>onCreate()</code>), it sets the layout from <code>activity_second.xml</code>.</li>
  <li>It retrieves the username passed from the MainActivity using the intent.</li>
  <li>It displays a message in a TextView welcoming the user and mentioning an offer (in this case, free access to content for one month).</li>
  <li>It also logs various lifecycle events (<code>onStart()</code>, <code>onResume()</code>, <code>onPause()</code>, <code>onStop()</code>, <code>onDestroy()</code>, <code>onRestart()</code>).</li>
</ul>

<p>Overall, this application demonstrates the flow of activity lifecycle methods in Android and how to pass data between activities using intents.</p>
