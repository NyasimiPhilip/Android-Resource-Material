// Define the package for the InfoActivity class
package com.android.recyclerbylazy

// Import necessary Android and Compose libraries
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.recyclerbylazy.model.TvShow

// Define the InfoActivity class, which extends ComponentActivity
class InfoActivity : ComponentActivity() {
    // Companion object to hold constants and utility functions
    companion object{
        // Constant for passing TvShowId between activities
        private const val TvShowId = "tvshowid"

        // Function to create an Intent for starting InfoActivity with TvShow data
        fun intent(context: Context,tvShow: TvShow)=
            Intent(context,InfoActivity::class.java).apply {
                putExtra(TvShowId,tvShow)
            }
    }

    // Lazy property to retrieve TvShow data from Intent extra
    private val tvShow : TvShow by lazy {
        intent?.getSerializableExtra(TvShowId) as TvShow
    }

    // Override the onCreate method to set the content view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view using the ViewMoreInfo Composable function
        setContent {
            ViewMoreInfo(tvShow = tvShow)
        }
    }
}

// Composable function to display detailed information about a TV show
@Composable
fun ViewMoreInfo(tvShow: TvShow) {
    // Remember the scroll state for vertical scrolling
    val scrollState = rememberScrollState()

    // Card composable to create a card with elevated design
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        // Set card color using MaterialTheme
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        // Set rounded corners for the card
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        // Column composable to arrange child composables vertically
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ) {
            // Image composable to display the TV show poster
            Image(
                painter = painterResource(id = tvShow.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            // Spacer to add vertical space between components
            Spacer(modifier = Modifier.height(16.dp))
            // Text composable to display the TV show name
            Text(
                text = tvShow.name,
                style = MaterialTheme.typography.headlineMedium
            )
            // Spacer for additional vertical space
            Spacer(modifier = Modifier.height(16.dp))
            // Text composable to display the TV show overview
            Text(
                text = tvShow.overview,
                style = MaterialTheme.typography.headlineSmall
            )
            // Spacer for additional vertical space
            Spacer(modifier = Modifier.height(16.dp))
            // Text composable to display the TV show original release year
            Text(
                text = "Original release : ${tvShow.year}",
                style = MaterialTheme.typography.headlineSmall
            )
            // Spacer for additional vertical space
            Spacer(modifier = Modifier.height(16.dp))
            // Text composable to display the TV show IMDB rating
            Text(
                text = "IMDB : ${tvShow.rating}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

// Preview function to demonstrate the UI of ViewMoreInfo in the Compose preview
@Preview
@Composable
fun ViewMoreInfoPreview() {
    // Create a sample TV show for preview
    val sampleTvShow = TvShow(
        name = "Sample TV Show",
        overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        year = 2022,
        rating = 8.5,
        id = 12,
        imageId = R.drawable.test
    )

    // Display the ViewMoreInfo composable with the sample TV show data
    ViewMoreInfo(tvShow = sampleTvShow)
}












