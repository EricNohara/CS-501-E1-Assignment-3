package com.example.myq1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myq1.ui.theme.MyQ1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                WeightedRowAndColumnScreen()
            }
        }
    }
}

@Composable
fun WeightedRowAndColumnScreen() {
    Row (
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        // Left section (25%)
        Box(
            modifier = Modifier
                .weight(1f)               // 1 part
                .fillMaxHeight()
                .background(Color(0xFFBBDEFB)), // light blue
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Left\n(25%)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Right section (75%)
        Box(
            modifier = Modifier
                .weight(3f)               // 1 part
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier.fillMaxSize()
            ) {
                // Top section weight 2
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .background(Color(0xFFFFCDD2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Top\n(Weight 2)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Middle section weight 3
                Box(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxSize()
                        .background(Color(0xFFC8E6C9)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Middle\n(Weight 3)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Bottom section weight 5
                Box(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxSize()
                        .background(Color(0xFFCFD8DC)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Bottom\n(Weight 5)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyQ1Theme {
        WeightedRowAndColumnScreen()
    }
}