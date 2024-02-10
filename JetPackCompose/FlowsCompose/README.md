<h1>Introduction:</h1>
<p>The FlowsCompose app demonstrates the usage of Kotlin Flows with coroutines in an Android application built with Jetpack Compose. It includes a ViewModel class MyViewModel that emits a flow of integers and a MainActivity that observes this flow and displays the latest emitted value using a Composable function.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Flow Emission:</strong> Emits integers from 1 to 100 at one-second intervals.</li>
  <li><strong>Back Pressure Handling:</strong> Demonstrates back pressure handling by filtering and mapping emitted values before collecting them with a delay.</li>
  <li><strong>Buffering:</strong> Shows how to handle back pressure by introducing buffering using the buffer operator.</li>
  <li><strong>Latest Value Collection:</strong> Uses collectAsState to collect the latest emitted value and update the UI accordingly.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MyViewModel:</strong> ViewModel class responsible for emitting a flow of integers and demonstrating back pressure handling and buffering.</li>
  <li><strong>MainActivity:</strong> Entry point of the application. Initializes the ViewModel and sets the content using Jetpack Compose.</li>
  <li><strong>MyApp Composable:</strong> Composable function that observes the flow emitted by the ViewModel and displays the latest emitted value.</li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>The MyViewModel emits integers from 1 to 10 at one-second intervals.</li>
  <li>In the MainActivity, the MyApp composable function collects the latest emitted value from the flow using collectAsState.</li>
  <li>The latest value is displayed on the UI using Jetpack Compose's Text composable.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Kotlin Flows:</strong> Used to emit a sequence of values asynchronously.</li>
  <li><strong>Back Pressure Handling:</strong> Implemented to handle scenarios where the flow producer emits values faster than the consumer can process them.</li>
  <li><strong>Buffering:</strong> Introduced using the buffer operator to handle back pressure by allowing buffering of emitted values.</li>
  <li><strong>Composable Functions:</strong> Used to define UI components in a declarative manner with Jetpack Compose.</li>
  <li><strong>ViewModels:</strong> Utilized to manage UI-related data in a lifecycle-aware manner.</li>
</ul>
