package com.example.aa_audio_streaming_and_effects_demo

import android.media.MediaPlayer
import android.os.Bundle
// Lightweight activty exten using Jetpack Compose.
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
// Import dp (density-independent pixels) for consistent sizing across different screen densities.
import androidx.compose.ui.unit.dp

// Define MainActivity that extends ComponentActivity for using Jetpack Compose.
class MainActivity : ComponentActivity() {
    // Declare a nullable MediaPlayer to handle audio playback.
    private var mediaPlayer: MediaPlayer? = null
    // Declare a Boolean to track if the media is currently playing.
    private var isPlaying = false

    // Override onCreate to set up the activity when it starts.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity using Jetpack Compose.
        setContent {
            // Pass state and event handlers to the AudioControls composable function.
            AudioControls(
                isPlaying = isPlaying,
                onPlayPauseClicked = { togglePlayPause() },
                onStopClicked = { stopPlayback() }
            )
        }
        // Initialize MediaPlayer with an audio file.
        initializeMediaPlayer()
    }

    // Initialize MediaPlayer with a specific audio resource.
    private fun initializeMediaPlayer() {
        // Create MediaPlayer for a specific audio file located in the raw resource folder.
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio) // Ensure you have a sample_audio.mp3 in res/raw
    }

    // Toggle play or pause of the MediaPlayer.
    private fun togglePlayPause() {
        // Check if MediaPlayer is initialized and playing.
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause() // Pause the MediaPlayer if it's currently playing.
                isPlaying = false // Update the isPlaying state to false.
            } else {
                it.start() // Start the MediaPlayer if it's not playing.
                isPlaying = true // Update the isPlaying state to true.
            }
        }
    }

    // Stop the playback of the MediaPlayer and prepare it for reuse.
    private fun stopPlayback() {
        mediaPlayer?.stop() // Stop the MediaPlayer.
        mediaPlayer?.prepare() // Prepare the MediaPlayer for playing the same audio again.
        isPlaying = false // Update the isPlaying state to false.
    }

    // Override onDestroy to clean up MediaPlayer when the activity is destroyed.
    override fun onDestroy() {
        mediaPlayer?.release() // Release the MediaPlayer resources.
        mediaPlayer = null // Nullify the MediaPlayer reference.
        super.onDestroy() // Call the superclass method.
    }
}

// Define a Composable function for the UI controls of the audio player.
@Composable
fun AudioControls(
    isPlaying: Boolean, // State indicating whether the audio is playing.
    onPlayPauseClicked: () -> Unit, // Lambda function for handling play/pause button clicks.
    onStopClicked: () -> Unit // Lambda function for handling stop button clicks.
) {
    // Arrange components vertically and center them.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp) // Fill the maximum size and add padding.
    ) {
        // Define a Play/Pause button.
        Button(
            onClick = { onPlayPauseClicked() }, // Set the click handler.
            modifier = Modifier.fillMaxWidth().padding(8.dp) // Set the button to fill the width and add padding.
        ) {
            // Display the appropriate text based on the isPlaying state.
            Text(if (isPlaying) "Pause" else "Play")
        }
        // Define a Stop button.
        Button(
            onClick = { onStopClicked() }, // Set the click handler.
            modifier = Modifier.fillMaxWidth().padding(8.dp) // Set the button to fill the width and add padding.
        ) {
            // Set the text of the button.
            Text("Stop")
        }


    }
}