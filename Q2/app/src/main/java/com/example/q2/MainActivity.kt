package com.example.q2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                ProfileWithBadge()
            }
        }
    }
}

@Composable
fun ProfileWithBadge() {
    var displayBadge by rememberSaveable { mutableStateOf(true) }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Container for the profile picture and badge
        Box (
            modifier = Modifier.size(150.dp),
            contentAlignment = Alignment.Center
        ) {
            // Profile Picture - image clipped in the shape of circle
            Image (
                painter = painterResource(id = R.drawable.blank_profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize().clip(CircleShape)
            )

            // Toggleable Badge
            if (displayBadge) {
                Box (
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) { }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Toggle Button for Badge
        Button(
            onClick = { displayBadge = !displayBadge }
        ) {
            Text(if (displayBadge) "Hide Badge" else "Show Badge")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        ProfileWithBadge()
    }
}