
<h1>ViewModel Demo 1</h1>
<p>The ViewModel Demo 1 Android app demonstrates the usage of ViewModel architecture component to manage and update UI data. It includes a simple UI with a text view displaying a count value and a button to increment the count.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Count Display:</strong> Displays the current count value on the screen.</li>
  <li><strong>Increment Button:</strong> Clicking the button increments the count value.</li>
</ul>

<h2>Components:</h2>
<dl>
  <dt>MainActivity (com.example.viewmodeldemo1.MainActivity):</dt>
  <dd>
    <ul>
      <li>Entry point of the app.</li>
      <li>Sets up the UI using data binding.</li>
      <li>Initializes the ViewModel and observes changes in the count LiveData.</li>
      <li>Updates the UI with the current count value and handles click events for the button.</li>
    </ul>
  </dd>
  
  <dt>MainActivityViewModel (com.example.viewmodeldemo1.MainActivityViewModel):</dt>
  <dd>
    <ul>
      <li>ViewModel class responsible for managing the count data.</li>
      <li>Utilizes MutableLiveData to allow changes to be observed.</li>
      <li>Exposes LiveData to observe changes in the count value.</li>
      <li>Provides functions to get the current count value and update the count.</li>
    </ul>
  </dd>
</dl>

<h2>Data Flow:</h2>
<ol>
  <li><strong>Initialization:</strong> The MainActivity initializes the ViewModel (MainActivityViewModel) and sets up observers to listen for changes in the count LiveData.</li>
  <li><strong>Observation:</strong> The count LiveData in the ViewModel is observed by the MainActivity. Whenever the count value changes, the UI is updated accordingly.</li>
  <li><strong>User Interaction:</strong> When the user clicks the button in the UI, the MainActivity triggers the getUpdatedCount function in the ViewModel.</li>
  <li><strong>ViewModel Update:</strong> Inside the ViewModel, the count value is incremented by one using the getUpdatedCount function. The new count value is then propagated to the observers through LiveData, triggering UI updates.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>ViewModel:</strong> Manages UI-related data in a lifecycle-aware manner. Survives configuration changes, such as screen rotations, ensuring data consistency. Separates UI-related data handling from UI controller logic.</li>
  <li><strong>LiveData:</strong> Observable data holder class provided by the Android Architecture Components. Allows observing changes in data and updating the UI accordingly.</li>
</ul>
