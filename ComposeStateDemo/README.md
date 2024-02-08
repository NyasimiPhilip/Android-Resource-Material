<h2>Compose State Demo App</h2>
<p>The Compose State Demo app is a simple Android application built using Jetpack Compose, which demonstrates the fundamental concepts of state management in a user interface. The app features a single screen with a button that increments a counter when clicked, displaying the current count value.</p>

<h2>Features:</h2>
<h3>Counter Button:</h3>
<ul>
  <li>The main feature of the app is a button that displays the current count value.</li>
  <li>Clicking the button increments the count value.</li>
</ul>

<h2>Components:</h2>
<dl>
  <dt>MainActivity (com.example.composestatedemo.MainActivity):</dt>
  <dd>
    <ul>
      <li>The entry point of the app.</li>
      <li>Sets the content of the activity using Compose.</li>
      <li>Retrieves the ViewModel using viewModel composable function.</li>
      <li>Applies the theme and layout using ComposeStateDemoTheme and Column composable.</li>
      <li>Contains a single composable MyButton.</li>
    </ul>
  </dd>
  
  <dt>MyButton Composable (com.example.composestatedemo.MyButton):</dt>
  <dd>
    <ul>
      <li>Defines a button with an onClick listener to update the count.</li>
      <li>Displays the current count value as text within the button.</li>
      <li>Uses Material Design styling for the button appearance.</li>
    </ul>
  </dd>
  
  <dt>MyViewModel (com.example.composestatedemo.MyViewModel):</dt>
  <dd>
    <ul>
      <li>Manages the state of the count value using a mutable state (count).</li>
    </ul>
  </dd>
</dl>

<h2>Data Flow in Compose State Demo App</h2>
<p>In the Compose State Demo app, the flow of data revolves around the count value, which represents the number of times the button has been clicked. Let's explore how this integer value originates and traverses between the components:</p>

<h3>Initialization:</h3>
<ul>
  <li>The count value is initially set to 0 in the MyViewModel class.</li>
</ul>

<h3>ViewModel Initialization:</h3>
<ul>
  <li>When the MainActivity is created, it retrieves an instance of MyViewModel using the viewModel composable function.</li>
</ul>

<h3>Button Click:</h3>
<ul>
  <li>When the button is clicked, the onClick listener in the MyButton composable calls the updateCount function provided by the MainActivity.</li>
  <li>This function updates the count value in the MyViewModel by invoking the increaseCount method.</li>
</ul>

<h3>State Mutation:</h3>
<ul>
  <li>The increaseCount method in the MyViewModel increments the count value by one.</li>
  <li>Since the count value is managed using a mutable state (mutableIntStateOf), any changes to it trigger recomposition of composables that depend on it.</li>
</ul>