## Kotlin Coroutine Usage in Android Application

### Imports:

The necessary classes and functions are imported, including `AppCompatActivity`, `Bundle`, `TextView`, `ProgressBar`, `Toast`, and various coroutine-related classes from the `kotlinx.coroutines` package.

### MainActivity class:

This class extends `AppCompatActivity` and represents the main activity of the Android application.

### `onCreate` method:

This method is called when the activity is first created. Here's what happens inside it:

- The layout file `activity_main.xml` is set as the content view of the activity.
- References to the `TextView` (`totalStockTextView`) and `ProgressBar` (`progressBar`) UI elements are obtained using their IDs.
- Coroutine Scope: A coroutine scope is created using `CoroutineScope(Dispatchers.Main)`. This specifies that the coroutine runs on the main thread, which is necessary for updating UI elements.
  
### Coroutine Block:

Inside the coroutine block, asynchronous operations are performed concurrently using `async`:

- Two coroutine functions, `getStock1()` and `getStock2()`, are called asynchronously using `async(Dispatchers.IO)`. These functions simulate fetching stock data with delays.
- `await()` is used to wait for the results of both asynchronous operations and retrieve their values.
- The total stock value is calculated by adding the results of `stock1.await()` and `stock2.await()`.

### UI Updates:

After the asynchronous operations are complete, UI updates are performed:

- A toast message is displayed showing the total stock value.
- The `totalStockTextView` is updated with the total stock value.
- The progress bar is hidden (`progressBar.visibility = ProgressBar.INVISIBLE`).

### Coroutine Functions:

- `getStock1()`: This coroutine function simulates fetching stock data with a delay of 3000 milliseconds (3 seconds) and returns a value of 55000.
- `getStock2()`: This coroutine function simulates fetching stock data with a delay of 500 milliseconds (0.5 seconds) and returns a value of 35000.

Overall, this code efficiently handles asynchronous operations using coroutines, ensuring a smooth user experience without blocking the UI thread.
