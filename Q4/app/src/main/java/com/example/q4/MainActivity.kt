package com.example.q4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.q4.ui.theme.Q4Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Q4Theme {
                ScaffoldScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen () {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf("Home") }

    Scaffold(
        // Top Bar with title
        topBar = { TopAppBar(title = { Text("Q4 Scaffold App Top Bar") }) },
        //Bottom bar with navigation menu
        bottomBar = {
            NavigationBar{
                // Navigation menu items with icons
                val items = listOf("Home", "Settings", "Profile")
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem.value == item,
                        // Toggle the button when clicked
                        onClick = { selectedItem.value = item },
                        icon = {
                            // Match the icon to the current item
                            Icon(
                                imageVector = when(item) {
                                    "Home" -> Icons.Default.Home
                                    "Settings" -> Icons.Default.Settings
                                    "Profile" -> Icons.Default.Person
                                    else -> Icons.Default.Home
                                },
                                contentDescription = item
                            )
                        },
                        label = { Text(item) }
                    )
                }
            }
        },
        // FAB that triggers a snackbar message
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("FAB clicked!")
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        innerPadding ->
        // main content
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Main content of the app")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Q4Theme {
        ScaffoldScreen()
    }
}