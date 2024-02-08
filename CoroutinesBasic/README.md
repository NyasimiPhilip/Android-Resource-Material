<h1>Coroutines in Android Development</h1>
<h2>Purpose:</h2>
<p>The purpose of this code is to showcase how to use coroutines in Android development to perform asynchronous tasks without blocking the main thread. Coroutines are a powerful feature in Kotlin for handling asynchronous operations, and they are particularly useful in Android apps where responsiveness is crucial.</p>

<h2>Key Components:</h2>
<ul>
  <li><strong>CoroutineScope:</strong> CoroutineScope is a way to manage the lifecycle of coroutines. It defines a scope within which coroutines can be launched. In this code, two instances of CoroutineScope are created: one with Dispatchers.IO and another with Dispatchers.Main.</li>
  <li><strong>Dispatchers:</strong> Dispatchers define the context in which coroutines run. Dispatchers.IO is optimized for IO-bound tasks (e.g., network operations, file operations) and is suitable for offloading work to background threads. Dispatchers.Main is used for operations that need to interact with the UI, such as updating UI elements.</li>
  <li><strong>Launching Coroutines:</strong> Inside the onCreate method of the activity, two coroutines are launched using different dispatchers. The first coroutine is launched in the IO dispatcher. It logs a message indicating it is running on a background thread. The second coroutine is launched in the Main dispatcher. It logs a message indicating it is running on the main UI thread.</li>
</ul>

<h2>Explanation:</h2>
<p>The onCreate method is a standard lifecycle method in Android activities, called when the activity is created. setContentView(R.layout.activity_main) sets the layout for the activity. Two coroutines are launched asynchronously using CoroutineScope.launch. The first coroutine logs a message using Log.i indicating it's running in the IO dispatcher, while the second coroutine logs a message indicating it's running in the Main dispatcher. By using coroutines with different dispatchers, the app can perform background tasks efficiently without blocking the main UI thread, ensuring smooth user experience</p>
