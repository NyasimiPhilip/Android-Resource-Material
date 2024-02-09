<h1>Introduction:</h1>
<p>The RetrofitDemo app demonstrates the usage of Retrofit, a powerful HTTP client library for Android and Java, for interacting with a RESTful API. It showcases features such as making GET and POST requests, handling API responses, and integrating with LiveData for asynchronous operations. The app is built around the JSONPlaceholder API, which provides a set of simulated endpoints for testing and learning purposes.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Making GET requests:</strong> Fetches data from the JSONPlaceholder API.</li>
  <li><strong>Making POST requests:</strong> Uploads data to the JSONPlaceholder API.</li>
  <li><strong>Handling API responses:</strong> Uses Retrofit's Response class for handling responses.</li>
  <li><strong>Utilizing LiveData:</strong> Observes API responses asynchronously.</li>
  <li><strong>Configuring OkHttpClient:</strong> Configures logging and timeout options for network operations.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity:</strong> Initiates API calls, observes responses, and updates the UI.</li>
  <li><strong>AlbumService:</strong> Retrofit service interface defining methods for API endpoints.</li>
  <li><strong>RetrofitInstance:</strong> Singleton class providing a configured Retrofit instance.</li>
  <li><strong>AlbumItem:</strong> Data class representing an item in the Album, parsed from JSON responses.</li>
  <li><strong>DetailsActivity:</strong> Activity for displaying details of a specific album item.</li>
  <li><strong>SettingsActivity:</strong> Activity for configuring app settings.</li>
</ul>

<h2>Data Flow:</h2>
<p><strong>MainActivity:</strong> Initiates API calls using methods defined in AlbumService. AlbumService interfaces with Retrofit to execute HTTP requests asynchronously. RetrofitInstance provides a configured Retrofit instance for making API calls. API responses are observed using LiveData in MainActivity, and the UI is updated accordingly. OkHttpClient handles network operations, including logging and timeout settings.</p>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Retrofit:</strong> A powerful HTTP client library simplifying communication with RESTful web services.</li>
  <li><strong>LiveData:</strong> A lifecycle-aware observable data holder class for handling asynchronous data streams.</li>
  <li><strong>OkHttpClient:</strong> An HTTP client for making network requests with features like logging and timeouts.</li>
</ul>

<p><strong>Note:</strong> The RetrofitDemo app interacts with the JSONPlaceholder API, which provides simulated endpoints for testing purposes. Ensure proper error handling and exception management to handle network-related issues effectively.</p>
</body>
