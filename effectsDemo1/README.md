<h1>Introduction:</h1>
<p>The EffectsDemo1 app is a simple Android application that demonstrates the use of Jetpack Compose to create a UI for counting values. It includes text input fields, buttons, and a snackbar to provide feedback to the user. The app allows users to input numerical values and keeps track of the total sum. When the total exceeds 200, it resets the total sum and displays a snackbar message indicating the exceeding condition.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Input Field:</strong> Allows users to enter numerical values.</li>
  <li><strong>Total Display:</strong> Displays the current total sum of the entered values.</li>
  <li><strong>Button:</strong> Initiates the counting process and updates the total sum.</li>
  <li><strong>Snackbar:</strong> Displays a message when the total sum exceeds 200.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity:</strong> Acts as the entry point of the application. Sets up the Jetpack Compose UI by invoking the setContent function with the EffectsDemo1Theme. Renders the MainScreen composable function inside a Surface container.</li>
  <li><strong>MainScreen Composable:</strong>
    <ul>
      <li><strong>Variables:</strong> Tracks the current round, total sum, and user input using mutable state variables.</li>
      <li><strong>CoroutineScope:</strong> Manages coroutine operations for displaying snackbar messages.</li>
      <li><strong>SnackbarHostState:</strong> Manages the state of the snackbar host.</li>
      <li><strong>LaunchedEffect:</strong> Commented out, but could be used to display a snackbar when the total exceeds 200.</li>
      <li><strong>Scaffold:</strong> Provides the basic layout structure for the screen, including the snackbar host and content.</li>
      <li><strong>Column:</strong> Arranges child composables vertically with space evenly distributed.</li>
      <li><strong>Text:</strong> Displays the current total sum.</li>
      <li><strong>OutlinedTextField:</strong> Allows users to input numerical values.</li>
      <li><strong>Button:</strong> Initiates the counting process and updates the total sum. Displays a snackbar message if the total exceeds 200.</li>
    </ul>
  </li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>User inputs a numerical value into the text field.</li>
  <li>User clicks the "Count" button.</li>
  <li>The app validates the input and updates the total sum.</li>
  <li>If the total sum exceeds 200, the app resets the total sum to 0 and displays a snackbar message.</li>
  <li>The user can continue entering values and counting rounds.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Jetpack Compose:</strong> Utilized for building the UI using a declarative approach.</li>
  <li><strong>State Management:</strong> Employed mutable state variables to manage the state of UI elements and trigger recomposition.</li>
  <li><strong>CoroutineScope:</strong> Used to launch coroutine operations for displaying snackbar messages asynchronously.</li>
  <li><strong>Snackbar:</strong> Integrated to provide feedback to the user when certain conditions are met, such as exceeding a threshold value.</li>
  <li><strong>Material Design Components:</strong> Leveraged MaterialTheme, Scaffold, OutlinedTextField, Button, and SnackbarHostState from the Material Design library for consistent UI design and behavior.</li>
</ul>
