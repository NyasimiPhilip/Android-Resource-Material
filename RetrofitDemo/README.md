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
  <li><strong>MainActivity:</strong> Initiates API calls using Retrofit's service interface (AlbumService), observes API responses, and updates the UI accordingly.</li>
  <li><strong>AlbumService:</strong> Retrofit service interface defining methods for API endpoints, including GET and POST requests to the JSONPlaceholder API.</li>
  <li><strong>RetrofitInstance:</strong> Singleton class providing a configured Retrofit instance with the base URL of the JSONPlaceholder API, Gson converter factory, OkHttpClient with logging, and timeout settings.</li>
  <li><strong>AlbumItem:</strong> Data class representing an item in the Album, parsed from JSON responses received from the JSONPlaceholder API.</li>
  <li><strong>DetailsActivity:</strong> Activity for displaying details of a specific album item retrieved from the JSONPlaceholder API.</li>
  <li><strong>SettingsActivity:</strong> Activity for configuring app settings.</li>
</ul>

<h2>Data Flow:</h2>
<p><strong>MainActivity:</strong> Initiates API calls using methods defined in AlbumService. AlbumService interfaces with Retrofit to execute HTTP requests asynchronously. RetrofitInstance provides a configured Retrofit instance for making API calls to the JSONPlaceholder API. API responses are observed using LiveData in MainActivity, and the UI is updated accordingly. OkHttpClient handles network operations, including logging and timeout settings.</p>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Retrofit:</strong> A widely-used HTTP client library for Android and Java applications, simplifying communication with RESTful web services like the JSONPlaceholder API.</li>
  <li><strong>LiveData:</strong> A lifecycle-aware observable data holder class, used for handling asynchronous data streams, particularly useful for observing API responses.</li>
  <li><strong>OkHttpClient:</strong> An HTTP client for Android and Java applications, used for making network requests with features like logging and timeouts, essential for handling network operations effectively.</li>
</ul>
