@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.gymmate.caloriespage.CaloriesPage
import com.example.gymmate.data.GenerateWorkout
import com.example.gymmate.data.ReadExerciseCSV
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.data.userdata.User
import com.example.gymmate.questionpage.QuestionPage
import com.example.gymmate.ui.theme.Theme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme(darkTheme = false) {
                GymmateApp()
            }
        }


    }
}