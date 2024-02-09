<h1>LiveData Fundamnentals</h1>

<p>This code showcases the usage of ViewModel along with a ViewModelFactory to manage and retain UI-related data across configuration changes. It utilizes LiveData to observe changes in the data and update the UI accordingly.</p>

<h2>Features:</h2>
<ul>
  <li><strong>ViewModel and ViewModelFactory Integration:</strong>
    <ul>
      <li>Implements a ViewModel class (MainActivityViewModel) to manage the total data.</li>
      <li>Utilizes a ViewModelFactory (MainActivityViewModelFactory) to create instances of the ViewModel with initial starting total.</li>
    </ul>
  </li>
  <li><strong>LiveData Observers:</strong>
    <ul>
      <li>Observes changes to the total data using LiveData in the MainActivity.</li>
      <li>Updates the UI automatically when the total data changes.</li>
    </ul>
  </li>
  <li><strong>Data Binding:</strong>
    <ul>
      <li>Utilizes Data Binding to bind UI components directly to the ViewModel.</li>
    </ul>
  </li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity (com.example.viewmodeldemo2.MainActivity):</strong>
    <ul>
      <li>Entry point of the app responsible for setting up the UI and observing LiveData changes.</li>
      <li>Initializes the ViewModelFactory with a starting total value and creates an instance of MainActivityViewModel using ViewModelProvider.</li>
      <li>Observes changes in the total data and updates the UI accordingly.</li>
    </ul>
  </li>
  <li><strong>MainActivityViewModel (com.example.viewmodeldemo2.MainActivityViewModel):</strong>
    <ul>
      <li>Manages the total data using LiveData to observe changes and update the UI.</li>
      <li>Provides a method to update the total data based on user input.</li>
    </ul>
  </li>
  <li><strong>MainActivityViewModelFactory (com.example.viewmodeldemo2.MainActivityViewModelFactory):</strong>
    <ul>
      <li>Custom ViewModelFactory responsible for creating instances of MainActivityViewModel.</li>
      <li>Accepts a starting total value and initializes the ViewModel with it.</li>
    </ul>
  </li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li><strong>Initialization:</strong>
    <ul>
      <li>Upon launching the app, the MainActivity initializes the ViewModelFactory with a starting total value.</li>
      <li>The ViewModelFactory creates an instance of MainActivityViewModel with the initial total.</li>
    </ul>
  </li>
  <li><strong>Data Management:</strong>
    <ul>
      <li>The user interacts with the UI by entering a number in the EditText and clicking the Insert button.</li>
      <li>The MainActivityViewModel receives the user input and updates the total data accordingly.</li>
    </ul>
  </li>
  <li><strong>LiveData Observation:</strong>
    <ul>
      <li>The MainActivity observes changes in the total data using LiveData.</li>
      <li>When the total data changes, the UI is automatically updated to display the new total value.</li>
    </ul>
  </li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>ViewModel:</strong>
    <ul>
      <li>Manages UI-related data in a lifecycle-aware manner.</li>
      <li>Survives configuration changes and retains data integrity across UI components.</li>
    </ul>
  </li>
  <li><strong>ViewModelFactory:</strong>
    <ul>
      <li>Custom factory class responsible for creating instances of ViewModel with constructor parameters.</li>
    </ul>
  </li>
  <li><strong>LiveData:</strong>
    <ul>
      <li>Observable data holder provided by the Android Architecture Components.</li>
      <li>Allows observing changes in data and updating the UI automatically, ensuring data consistency and proper lifecycle management.</li>
    </ul>
  </li>
  <li><strong>Data Binding:</strong>
    <ul>
      <li>Simplifies the process of binding UI components to data in the ViewModel, reducing boilerplate code.</li>
    </ul>
  </li>
</ul>

<p><strong>Note:</strong> The ViewModel Demo 2 app demonstrates a typical implementation of ViewModel along with ViewModelFactory and LiveData to manage and update UI-related data efficiently.</p>
