<h1>Hilt Demo</h1>
<p>The Hilt Demo Android app illustrates the integration of Hilt, a dependency injection library for Android, to manage and provide dependencies throughout the application. It showcases the setup and usage of Hilt within an Android project, allowing for streamlined dependency injection and improved code organization.</p>

<h2>Features:</h2>
<ul>
  <li><strong>Dependency Injection:</strong> Utilizes Hilt to facilitate dependency injection across various components of the application. Demonstrates the injection of a data source dependency into an activity using Hilt's annotations.</li>
  <li><strong>Data Retrieval:</strong> Illustrates the retrieval of remote data by utilizing the injected data source instance. Logs a message indicating the initiation of data downloading.</li>
</ul>

<h2>Components:</h2>
<dl>
  <dt>App (com.android.hiltdemo.App):</dt>
  <dd>
    <ul>
      <li>Application class annotated with @HiltAndroidApp to enable Hilt's functionality.</li>
      <li>Initializes Hilt within the application to handle dependency injection.</li>
    </ul>
  </dd>
  
  <dt>DataModule (com.android.hiltdemo.DataModule):</dt>
  <dd>
    <ul>
      <li>Dagger module responsible for providing instances of dependencies, such as the data source.</li>
      <li>Annotated with @:InstallIn(SingletonComponent::class) to specify the component where the module's bindings are installed.</li>
      <li>Provides an instance of the data source using the @Provides annotation.</li>
    </ul>
  </dd>
  
  <dt>DataSource (com.android.hiltdemo.DataSource):</dt>
  <dd>
    <ul>
      <li>Represents a mock data source class with a method to retrieve remote data.</li>
      <li>Used as a dependency within the application.</li>
    </ul>
  </dd>
  
  <dt>MainActivity (com.android.hiltdemo.MainActivity):</dt>
  <dd>
    <ul>
      <li>Activity class annotated with @AndroidEntryPoint to enable Hilt injection.</li>
      <li>Injects an instance of the data source using the @Inject annotation.</li>
      <li>Retrieves remote data by calling a method from the injected data source instance.</li>
    </ul>
  </dd>
</dl>

<h2>Data Flow:</h2>
<ol>
  <li><strong>Hilt Initialization:</strong> Upon application startup, Hilt is initialized within the App class using the @HiltAndroidApp annotation.</li>
  <li><strong>Dependency Provision:</strong> The DataModule provides an instance of the data source using the @Provides annotation, specifying how to create and provide the dependency.</li>
  <li><strong>Injection:</strong> In the MainActivity, Hilt injects an instance of the data source using the @Inject annotation, eliminating the need for manual instantiation.</li>
  <li><strong>Data Retrieval:</strong> The injected data source instance is used to retrieve remote data, demonstrating the functionality of the dependency injection setup.</li>
</ol>

<h2>Key Concepts:</h2>
<ul>
  <li><strong>Hilt:</strong> A dependency injection library for Android, built on top of Dagger, designed to simplify dependency injection implementation in Android applications.</li>
  <li><strong>Dependency Injection:</strong> A design pattern that involves passing dependencies to dependent objects rather than creating them internally. Improves code maintainability, testability, and flexibility by decoupling components and promoting single responsibility.</li>
  <li><strong>Dagger:</strong> A compile-time dependency injection framework for Java and Android. Used by Hilt under the hood to generate and manage dependency injection code.</li>
</ul>

<p><strong>Note:</strong> This Hilt Demo app serves as an introductory example of integrating Hilt into an Android project for dependency injection. It demonstrates the basic setup and usage of Hilt within an application context</p>
