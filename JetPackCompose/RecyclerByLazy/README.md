<h1>Introduction:</h1>
<p>The "RecyclerByLazy" app is a sample Android application built using Jetpack Compose. It allows users to browse a list of TV shows and view detailed information about each TV show. The app showcases the use of modern UI development techniques and Jetpack Compose's declarative UI approach to create a dynamic and engaging user experience.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Display TV Shows:</strong> Users can browse a list of TV shows, including their names, overviews, original release years, and IMDB ratings.</li>
  <li><strong>View More Info:</strong> Users can select a TV show from the list to view detailed information, including an image, name, overview, release year, and rating.</li>
  <li><strong>Dynamic UI:</strong> The UI is dynamically generated using Jetpack Compose's composables, enabling efficient rendering and smooth user interactions.</li>
  <li><strong>Clickable Items:</strong> TV show items in the list are clickable, allowing users to select a TV show to view more details.</li>
  <li><strong>Modern Styling:</strong> The app features modern Material Design styling with card views, rounded corners, and elevated surfaces.</li>
</ul>

<h2>Components:</h2>
<ul>
  <li><strong>MainActivity:</strong> Entry point of the app. Sets up the UI and displays the list of TV shows using Jetpack Compose's LazyColumn.</li>
  <li><strong>InfoActivity:</strong> Displays detailed information about a selected TV show. Utilizes Jetpack Compose to create a dynamic UI with image, text, and scrolling capabilities.</li>
  <li><strong>TvShowListItem Composable:</strong> Represents a single item (TV show) in the list. Displays the TV show's information using Jetpack Compose's UI components.</li>
  <li><strong>TvShowImage Composable:</strong> Displays the image of a TV show using Jetpack Compose's Image composable.</li>
  <li><strong>DisplayTvShows Composable:</strong> Displays the list of TV shows using a LazyColumn with each item represented by the TvShowListItem composable.</li>
</ul>

<h2>Data Flow:</h2>
<ol>
  <li>TV show data is provided by the TvShowList object, which contains a list of predefined TV show objects.</li>
  <li>The DisplayTvShows composable retrieves the list of TV shows from TvShowList and displays them using a LazyColumn.</li>
  <li>When a TV show item is clicked, the corresponding TvShow object is passed to the InfoActivity to display detailed information.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Jetpack Compose:</strong> Utilizes Jetpack Compose for building the UI using declarative and composable functions.</li>
  <li><strong>LazyColumn:</strong> Renders a large list of items efficiently, loading only the visible items on the screen.</li>
  <li><strong>Card Composable:</strong> Provides a material design card view with elevated surfaces and rounded corners for displaying TV show details.</li>
  <li><strong>Clickable Modifier:</strong> Enables items to be clickable, triggering actions when clicked by the user.</li>
  <li><strong>Material Design:</strong> Applies modern Material Design principles for styling and layout, enhancing the overall user experience.</li>
</ul>
