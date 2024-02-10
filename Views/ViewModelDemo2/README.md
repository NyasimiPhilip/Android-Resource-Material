<h1>Introduction:</h1>
<p>The ViewModel Demo 2 app is an Android application demonstrating the use of ViewModel and ViewModelFactory to manage and retain UI-related data. It features a simple functionality where users can input numeric values and increment a total count by clicking a button. The app showcases the implementation of ViewModel architecture component to separate UI logic from UI components and ensure data persistence across configuration changes.</p>

<h2>Feature Components:</h2>
<ul>
  <li><strong>MainActivity:</strong>
    <ul>
      <li>The main activity responsible for creating the user interface and handling user interactions.</li>
      <li>Uses Data Binding to bind UI elements to ViewModel properties.</li>
      <li>Initializes ViewModel and ViewModelFactory to manage the count data.</li>
    </ul>
  </li>
  <li><strong>MainActivityViewModel:</strong>
    <ul>
      <li>Extends the ViewModel class provided by Android Architecture Components.</li>
      <li>Manages the total count data and provides methods to retrieve and update the count.</li>
      <li>Ensures that the count data is retained across configuration changes using ViewModel's lifecycle awareness.</li>
    </ul>
  </li>
  <li><strong>MainActivityViewModelFactory:</strong>
    <ul>
      <li>Implements ViewModelProvider.Factory interface to create instances of MainActivityViewModel.</li>
      <li>Accepts a startingTotal value to initialize MainActivityViewModel with an initial count value.</li>
      <li>Ensures proper instantiation of MainActivityViewModel instances by ViewModelProvider.</li>
    </ul>
  </li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>Upon launching the app, MainActivity initializes its layout and binds UI components using Data Binding.</li>
  <li>MainActivity creates an instance of MainActivityViewModelFactory with a startingTotal value.</li>
  <li>MainActivity uses ViewModelProvider to create an instance of MainActivityViewModel with the help of MainActivityViewModelFactory.</li>
  <li>The initial total count value is retrieved from MainActivityViewModel and displayed in the UI.</li>
  <li>When the user enters a numeric value and clicks the button, MainActivity updates the count in MainActivityViewModel.</li>
  <li>MainActivityViewModel increments the total count and updates its state.</li>
  <li>MainActivity observes changes in MainActivityViewModel and updates the UI with the new total count value.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>ViewModel:</strong> Used to store and manage UI-related data in a lifecycle-aware manner, ensuring data persistence across configuration changes and device rotations.</li>
  <li><strong>ViewModelFactory:</strong> Enables the creation of ViewModel instances with custom initialization parameters, allowing flexibility in ViewModel instantiation.</li>
  <li><strong>Data Binding:</strong> Facilitates the binding of UI elements to ViewModel properties, reducing boilerplate code and enhancing code readability.</li>
  <li><strong>Lifecycle Awareness:</strong> ViewModel is scoped to the lifecycle of the associated activity or fragment, allowing it to automatically clean up resources when no longer needed and survive configuration changes.</li>
  <li><strong>Separation of Concerns:</strong> ViewModel separates the UI logic from UI components, promoting better code organization, testability, and maintenance.</li>
</ul>
