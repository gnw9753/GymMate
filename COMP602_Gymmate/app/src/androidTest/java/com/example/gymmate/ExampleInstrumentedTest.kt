package com.example.gymmate

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gymmate.data.GenerateWorkout
import com.example.gymmate.data.ReadExerciseCSV

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun generateWorkoutNotNull() {
        // Basic normal workout test
        composeTestRule.setContent {
            val context = LocalContext.current
            val inputStream = context.resources.openRawResource(R.raw.resist_train_planner)
            val csvFile = ReadExerciseCSV(inputStream)
            val exerciseCSV = csvFile.read()
            val daysAvailableBooleanList = listOf(true, false, true, false, true, false, true)
            val workoutList = GenerateWorkout(
                1,
                "gain muscle",
                daysAvailableBooleanList,
                exerciseCSV
            ).generateWorkout()

            assertNotNull("Workout list is not empty", workoutList.isNotEmpty())
        }
    }

    @Test
    fun generateWorkoutNull() {
        // Test if generateWorkout can handle an empty list
        // Typically requires a boolean list to know which days to generate a workout for
        composeTestRule.setContent {
            val context = LocalContext.current
            val inputStream = context.resources.openRawResource(R.raw.resist_train_planner)
            val csvFile = ReadExerciseCSV(inputStream)
            val exerciseCSV = csvFile.read()
            val daysAvailableBooleanList: List<Boolean> = listOf()
            val workoutList = GenerateWorkout(
                1,
                "gain muscle",
                daysAvailableBooleanList,
                exerciseCSV
            ).generateWorkout()

            assertTrue("Workout list is not empty", workoutList.isEmpty())
        }
    }

    @Test
    fun generateWorkoutForIncorrectExerciseGoal() {
        // If the goal set by the user does not match "gain muscle" or "lose weight"
        // It should still continue to generate a workout but random
        composeTestRule.setContent {
            val context = LocalContext.current
            val inputStream = context.resources.openRawResource(R.raw.resist_train_planner)
            val csvFile = ReadExerciseCSV(inputStream)
            val exerciseCSV = csvFile.read()
            val daysAvailableBooleanList = listOf(true, false, true, false, true, false, true)
            val workoutList =
                GenerateWorkout(1, "test1", daysAvailableBooleanList, exerciseCSV).generateWorkout()
            assertTrue("Workout list is empty", workoutList[0].exerciseList.isNotEmpty())
        }
    }
}