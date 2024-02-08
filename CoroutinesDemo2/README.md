<h1>Coroutines Demo 2</h1>
<p>The Coroutines Demo 1 Android app has been enhanced to demonstrate more advanced coroutine usage, including asynchronous data fetching and computation. It includes features to increment a count value and asynchronously fetch user data counts.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Count Button:</strong> Clicking the "Count" button increments a counter displayed on the screen.</li>
  <li><strong>Download User Data Button:</strong> Clicking the "Download User Data" button asynchronously fetches and displays the total user count.</li>
  <li><strong>TextViews:</strong> 
    <ul>
      <li>tvCount: Displays the current count value.</li>
      <li>tvUserMessage: Displays messages indicating the progress of user data fetching.</li>
    </ul>
  </li>
</ul>

<h2>Components:</h2>
<dl>
  <dt>MainActivity (com.example.coroutinesdemo1.MainActivity):</dt>
  <dd>
    <ul>
      <li>Entry point of the app.</li>
      <li>Initializes UI components and sets click listeners for buttons.</li>
      <li>Utilizes coroutines for asynchronous tasks.</li>
    </ul>
  </dd>
  
  <dt>UserDataManager (com.example.coroutinesdemo1.UserDataManager):</dt>
  <dd>
    <ul>
      <li>A utility class responsible for fetching user data asynchronously.</li>
      <li>Uses coroutines to perform asynchronous operations and return the total user count.</li>
    </ul>
  </dd>
</dl>

<h2>Coroutine Usage:</h2>
<dl>
  <dt>Count Incrementation:</dt>
  <dd>
    <ul>
      <li>Clicking the "Count" button increments the count value in a non-blocking manner.</li>
      <li>The count value is updated in the UI directly from the main thread.</li>
    </ul>
  </dd>
  
  <dt>User Data Fetching:</dt>
  <dd>
    <ul>
      <li>Clicking the "Download User Data" button initiates an asynchronous operation to fetch user data count.</li>
      <li>The getTotalUserCount function in UserDataManager asynchronously fetches user counts using coroutines.</li>
      <li>The fetched user counts are displayed in the tvUserMessage TextView.</li>
    </ul>
  </dd>
  
  <dt>Asynchronous Operations:</dt>
  <dd>
    <ul>
      <li>Asynchronous operations are performed using launch and async coroutine builders.</li>
      <li>launch is used for a delayed operation of 1 second, while async is used for a delayed operation of 3 seconds, returning a user count of 70.</li>
    </ul>
  </dd>
</dl>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Asynchronous Data Fetching:</strong> Demonstrates fetching data asynchronously using coroutines. Uses async to perform parallel asynchronous operations and await to wait for their completion.</li>
  <li><strong>Coroutine Scope:</strong> Uses coroutine scope to manage the lifecycle of coroutines and their cancellation</li>
</ul>
