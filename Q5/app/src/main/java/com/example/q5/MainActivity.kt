package com.example.q5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.q5.ui.theme.Q5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Q5Theme {
                ThemedForm()
            }
        }
    }
}

@Composable
fun ThemedForm() {
    // State variables
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var usernameError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }
    var loginMessage by rememberSaveable { mutableStateOf("") }

    // Container for form to center the form
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Form title
            Text("Login", style = MaterialTheme.typography.headlineMedium)

            // Spacer
            Spacer(modifier = Modifier.height(24.dp))

            // Username input
            OutlinedTextField(
                value = username,
                onValueChange = { username = it; usernameError = false },
                label = { Text("Username") },
                isError = usernameError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Username error message
            if (usernameError) {
                Text(
                    text = "Username cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Password input
            OutlinedTextField(
                value = password,
                onValueChange = { password = it; passwordError = false },
                label = { Text("Password") },
                isError = passwordError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Password error message
            if (passwordError) {
                Text(
                    text = "Password cannot be empty",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Login button
            Button(
                onClick = {
                    usernameError = username.isEmpty()
                    passwordError = password.isEmpty()
                    if (!usernameError && !passwordError) {
                        loginMessage = "User is logged in. Log in again"
                        username = ""
                        password = ""
                    } else {
                        loginMessage = "" // clear message if errors exist
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login", modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))
            }

            // Login success message
            if (loginMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = loginMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Q5Theme {
        ThemedForm()
    }
}