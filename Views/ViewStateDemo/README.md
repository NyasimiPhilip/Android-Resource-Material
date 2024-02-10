<h1>Introduction:</h1>
<p>The ViewStateDemo app is a simple Android application demonstrating the concept of view state management. It allows users to interact with a button that increments a counter displayed on the button itself. The application showcases how to maintain and update the state of a view component across configuration changes, such as screen rotation.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Button Counting:</strong> Users can click on a button to increment a counter.</li>
  <li><strong>Dynamic Button Text:</strong> The text of the button dynamically updates to display the current count.</li>
  <li><strong>Toast Notification:</strong> Upon clicking the button, a toast notification displays the current count.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity:</strong>
    <ul>
      <li>Represents the main activity of the application.</li>
      <li>Initializes the UI components and sets up the button click listener.</li>
      <li>Manages the count state and updates the button text accordingly.</li>
      <li>Displays a toast notification with the current count upon button click.</li>
    </ul>
  </li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>Upon launching the application, MainActivity is created and the UI layout is inflated.</li>
  <li>The button text is set to display the initial count value.</li>
  <li>When the button is clicked, the count value is incremented and the button text is updated to reflect the new count.</li>
  <li>A toast notification is displayed, showing the current count.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>View State Management:</strong> Demonstrates how to manage and update the state of a view component (button text) across configuration changes, ensuring a consistent user experience.</li>
  <li><strong>Button Interaction:</strong> Illustrates how users can interact with UI components (button) to trigger actions within the application.</li>
  <li><strong>Toast Notification:</strong> Utilizes toast notifications to provide feedback to the user upon button click, displaying the current count.</li>
</ul>
