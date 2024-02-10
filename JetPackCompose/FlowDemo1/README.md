<h1>Introduction:</h1>
<p>The FlowDemo1 app is a basic Android application that demonstrates the use of Kotlin Coroutines Flow to emit a sequence of integers at regular intervals and update a TextView to display the current index.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Flow Generation:</strong> Generates a sequence of integers from 1 to 100 at one-second intervals.</li>
  <li><strong>Text Display:</strong> Updates a TextView in the UI to show the current index emitted by the flow.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity:</strong> Acts as the main activity of the application. Sets the content view to the layout defined in activity_main.xml.</li>
  <li><strong>onCreate:</strong> Initializes the UI components and creates a flow of integers using flow.</li>
  <li><strong>myFlow:</strong> Defines a flow of integers emitting values from 1 to 100 at one-second intervals using the emit function and delay coroutine function.</li>
  <li><strong>textView:</strong> References the TextView component from the layout.</li>
  <li><strong>CoroutineScope.launch:</strong> Launches a coroutine on the main dispatcher to collect the latest value emitted by the flow and update the TextView accordingly.</li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>The app creates a flow that emits integers from 1 to 100 at one-second intervals.</li>
  <li>A coroutine is launched on the main dispatcher to collect the latest value emitted by the flow.</li>
  <li>Each time a new value is emitted by the flow, the TextView in the UI is updated to display the current index.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Kotlin Coroutines:</strong> Used for asynchronous programming.</li>
  <li><strong>Flow:</strong> Represents a sequence of values that can be asynchronously computed or emitted over time.</li>
  <li><strong>Collect Latest:</strong> A suspending function that collects values from the flow and updates the UI with the latest emitted value.</li>
  <li><strong>CoroutineScope:</strong> Provides a scope for launching coroutines. In this case, a coroutine is launched on the main dispatcher to interact with UI components.</li>
  <li><strong>Delay:</strong> Delays the execution of the coroutine for a specified amount of time, allowing for periodic emission of values in the flow.</li>
</ul>
