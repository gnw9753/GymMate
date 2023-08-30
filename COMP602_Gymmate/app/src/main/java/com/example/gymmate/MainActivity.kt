@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmate.ui.theme.GymmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymmateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview()
                }
            }
        }
    }
}

@Composable
fun MainHomePage(modifier: Modifier = Modifier) {
    Column(
    ) {
        DayDisplayCard("Monday","Cardio")
    }
}

@Composable
fun ExerciseDisplayCard(exerciseList: List<String>, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.size(width=200.dp, height = 100.dp),
        ) {

    }
}

@Composable
fun DayDisplayCard(day: String,exerciseType: String, modifier: Modifier = Modifier){
    Card(
        onClick = { /* Do something */ },
        modifier = Modifier.size(width = 180.dp, height = 100.dp)
    ) {
        Text(
            text = day,
            Modifier.padding(10.dp),
            fontSize = 20.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymmateTheme {
        MainHomePage()
    }
}