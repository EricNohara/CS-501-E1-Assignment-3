package com.example.q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                ContactListScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen() {
    // Generate 50 fake contacts in the form a1, a2, b3, b4, c5, ...
    val contacts = mutableListOf<String>()
    var letter = 'a'
    var counter = 1

    for (i in 1..50 step 2) {
        // Add two items per letter
        contacts.add("$letter$counter Contact")
        counter++
        if (contacts.size >= 50) break

        contacts.add("$letter$counter Contact")
        counter++
        if (contacts.size >= 50) break

        // Move to next letter
        letter++
        if (letter > 'z') letter = 'a'
    }

    // Group contacts alphabetically
    val grouped = remember {
        contacts.groupBy { it.first() }
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope ()

    // derived state to show button only after seeing the e10 contact
    val showScrollToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold (
        // Button for scrolling to the top of the list only visible after scrolling past 10 items
        floatingActionButton = {
            if (showScrollToTop) {
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Text(
                        "Scroll to Top",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    ) {
        paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            grouped.forEach { (letter, names) ->
                // Add a sticky header for the current letter
                stickyHeader {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.DarkGray
                    ) {
                        Text(
                            text = letter.toString(),
                            modifier = Modifier.padding(18.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White)
                        )
                    }
                }
                // Add contact item rows for every name grouped with that letter
                items(names) { contact -> ContactItem(contact) }
            }
        }
    }
}

@Composable
fun ContactItem(contact: String) {
    // Contact item representing a single row in the contact list
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = contact, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        ContactListScreen()
    }
}