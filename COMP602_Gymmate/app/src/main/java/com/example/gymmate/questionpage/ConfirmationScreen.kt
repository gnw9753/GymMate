package com.example.gymmate.questionpage
/*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymmatekotlin.UserViewModel

@Composable
fun ConfirmationScreen(userViewModel: UserViewModel, navController: NavController) {
    val userEmail = userViewModel.email
    val userName = userViewModel.name
    val userGender = userViewModel.gender
    val userAge = userViewModel.age
    val userHeight = userViewModel.height
    val userWeight = userViewModel.weight
    val userGoal = userViewModel.goal
    val userDays = userViewModel.day

    Column {
        ConfirmationHeader()
        EnteredDataItem("Email", userEmail)
        EnteredDataItem("Name", userName)
        EnteredDataItem("Gender", userGender)
        EnteredDataItem("Age", userAge.toString())
        EnteredDataItem("Height", userHeight.toString())
        EnteredDataItem("Weight", userWeight.toString())
        EnteredDataItem("Goal", userGoal)
        EnteredDataItem("Day", userDays.joinToString(", "))

        ButtonsRow(navController)
    }
}

@Composable
fun ConfirmationHeader() {
    Text(
        text = "Confirmation",
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun EnteredDataItem(label: String, value: String) {
    Text(
        text = "Entered $label: $value",
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun ButtonsRow(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        PreviousButton(navController)
        Spacer(modifier = Modifier.height(16.dp))
        ConfirmButton()
    }
}

@Composable
fun PreviousButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate("regi")
        },
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Previous", fontSize = 24.sp)
    }
}

@Composable
fun ConfirmButton() {
    Button(
        onClick = {
            // Handle confirmation logic here
        },
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Confirm", fontSize = 24.sp)
    }
}
*/