<h1>About CircleCalc Android Application</h1>
<p>This Android application, named CircleCalc, calculates the area and circumference of a circle based on the radius provided by the user. It utilizes the MVVM (Model-View-ViewModel) architecture along with data binding and LiveData to ensure separation of concerns and a responsive user interface.</p>

<h2>Components:</h2>
<dl>
  <dt>MainActivity (com.example.circlecalc.MainActivity):</dt>
  <dd>
    <ul>
      <li>Initializes and sets up the user interface (UI) and ViewModel.</li>
      <li>Uses data binding to bind UI elements with ViewModel properties.</li>
      <li>Sets lifecycle owner for observing LiveData updates.</li>
    </ul>
  </dd>
  
  <dt>CalcViewModel (com.example.circlecalc.CalcViewModel):</dt>
  <dd>
    <ul>
      <li>ViewModel responsible for handling calculations related to circles.</li>
      <li>Holds MutableLiveData for radius, area, and circumference.</li>
      <li>Exposes LiveData for area and circumference values to observers.</li>
      <li>Implements functions to perform circle calculations and update corresponding LiveData.</li>
    </ul>
  </dd>
  
  <dt>CalcViewModelFactory (com.example.circlecalc.CalcViewModelFactory):</dt>
  <dd>
    <ul>
      <li>Factory class for creating instances of CalcViewModel with dependencies.</li>
      <li>Used by ViewModelProvider to create ViewModel instances.</li>
    </ul>
  </dd>
  
  <dt>Calculations interface (com.example.circlecalc.Calculations):</dt>
  <dd>
    <ul>
      <li>Defines functions to calculate the circumference and area of a circle.</li>
      <li>Implemented by MyCalc class.</li>
    </ul>
  </dd>
  
  <dt>MyCalc class (com.example.circlecalc.MyCalc):</dt>
  <dd>
    <ul>
      <li>Implementation of the Calculations interface for circle calculations.</li>
      <li>Provides methods to calculate the circumference and area of a circle based on the radius.</li>
    </ul>
  </dd>
</dl>

<h2>Key Features:</h2>
<ul>
  <li>Utilizes MVVM architecture for separation of concerns and improved testability.</li>
  <li>Data binding simplifies UI updates by automatically synchronizing ViewModel data with UI components.</li>
  <li>LiveData ensures that UI remains updated with the latest calculation results.</li>
  <li>Factory pattern is employed to provide dependencies to the ViewModel.</li>
  <li>Exception handling is implemented in the ViewModel to handle invalid input or calculation errors gracefully.</li>
</ul>

<h2>Circle Calculator Unit Tests</h2>
<p>This project includes unit tests for various components of the Circle Calculator Android app. These tests ensure the correctness of calculations and the functionality of ViewModel methods.</p>

<h3>Components Tested:</h3>
<dl>
  <dt>CalcViewModelTest (com.example.circlecalc.CalcViewModelTest):</dt>
  <dd>
    <ul>
      <li>Tests the CalcViewModel class, which is responsible for handling calculations related to circles.</li>
      <li>Verifies that the ViewModel correctly updates LiveData values after performing calculations.</li>
      <li>Uses Mockito for mocking the Calculations dependency and stubbing its methods.</li>
      <li>Includes test cases for both calculateArea and calculateCircumference methods.</li>
    </ul>
  </dd>
  
  <dt>MyCalcTest (com.example.circlecalc.MyCalcTest):</dt>
  <dd>
    <ul>
      <li>Tests the MyCalc class, which implements the Calculations interface for circle calculations.</li>
      <li>Ensures that calculateCircumference and calculateArea methods return correct results for given radius inputs.</li>
      <li>Includes test cases for non-zero and zero radius inputs.</li>
    </ul>
  </dd>
  
  <dt>ExampleUnitTest (com.example.circlecalc.ExampleUnitTest):</dt>
  <dd>
    <ul>
      <li>An example local unit test provided by Android Studio template.</li>
      <li>Verifies the correctness of addition operation.</li>
    </ul>
  </dd>
</dl>

<h3>Key Features:</h3>
<ul>
  <li>Mockito Usage: CalcViewModelTest utilizes Mockito to mock the Calculations dependency, allowing controlled testing of ViewModel behavior.</li>
  <li>InstantTaskExecutorRule: Enforces synchronous execution of tasks in ViewModel tests, ensuring LiveData updates are immediately observable.</li>
  <li>Truth Assertion: Assertions are made using Google Truth library for clearer and more readable test cases.</li>
  <li>Comprehensive Coverage: Test cases cover a range of scenarios including normal calculation, edge cases (like zero radius), and boundary conditions.</li>
</ul>

<h2>Usage:</h2>
<ul>
  <li>Running Tests: Tests can be executed using the appropriate testing framework (JUnit in this case) integrated with the build system (e.g., Gradle).</li>
  <li>Test Results: Upon execution, the testing framework will report the success or failure of each test case, providing feedback on the correctness of the implemented functionality</li>
</ul>
