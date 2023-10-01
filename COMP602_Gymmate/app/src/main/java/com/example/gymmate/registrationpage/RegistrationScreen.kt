package com.example.gymmate.registrationpage
/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gymmate.GymmateRoute
import com.example.gymmatekotlin.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(userViewModel: UserViewModel, navController: NavController) {
    var email by remember { mutableStateOf(userViewModel.email) }
    var name by remember { mutableStateOf(userViewModel.name) }
    var gender by remember { mutableStateOf(userViewModel.gender) }
    var age by remember { mutableIntStateOf(userViewModel.age) }
    var height by remember { mutableIntStateOf(userViewModel.height) }
    var weight by remember { mutableIntStateOf(userViewModel.weight) }
    var goal by remember { mutableStateOf(userViewModel.goal) }
    var days by remember { mutableStateOf(List(DaysOfWeek.values().size) { false }) }
    var selectedDays by remember { mutableStateOf(emptyList<DaysOfWeek>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        RegistrationHeader()
        EmailInput(email, onEmailChanged = { email = it })
        NameInput(name, onNameChanged = { name = it })
        GenderSelection(gender, onGenderChanged = { gender = it })
        AgeInput(age, onAgeChanged = { age = it })
        HeightInput(height, onHeightChanged = { height = it })
        WeightInput(weight, onWeightChanged = { weight = it })
        GoalSelection(goal, onGoalChanged = { goal = it })
        WorkoutDaysSelection(selectedDays, onSelectedDaysChanged = { selectedDays = it })
        SignUpButton(userViewModel, email, name, gender, age, height, weight, goal, selectedDays, navController)
    }
}

@Composable
fun RegistrationHeader() {
    Text(
        text = "Create Your GymMate Account",
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun EmailInput(email: String, onEmailChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChanged(it) },
        label = { Text(text = "Email") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun NameInput(name: String, onNameChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChanged(it) },
        label = { Text(text = "Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun GenderSelection(gender: String, onGenderChanged: (String) -> Unit) {
    Text(
        text = "What is your gender?",
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Text(
        text = gender,
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GenderButton("Male", onGenderChanged)
        Spacer(modifier = Modifier.width(16.dp))
        GenderButton("Female", onGenderChanged)
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun GenderButton(text: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(text) },
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun AgeInput(age: Int, onAgeChanged: (Int) -> Unit) {
    OutlinedTextField(
        value = age.toString(),
        onValueChange = {
            // Update age as an Int when the user enters a valid number
            val newAge = it.toIntOrNull() ?: 0
            onAgeChanged(newAge)
        },
        label = { Text(text = "Age") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun HeightInput(height: Int, onHeightChanged: (Int) -> Unit) {
    OutlinedTextField(
        value = height.toString(),
        onValueChange = {
            // Update height as an Int when the user enters a valid number
            val newHeight = it.toIntOrNull() ?: 0
            onHeightChanged(newHeight)
        },
        label = { Text(text = "Height") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun WeightInput(weight: Int, onWeightChanged: (Int) -> Unit) {
    OutlinedTextField(
        value = weight.toString(),
        onValueChange = {
            // Update weight as an Int when the user enters a valid number
            val newWeight = it.toIntOrNull() ?: 0
            onWeightChanged(newWeight)
        },
        label = { Text(text = "Weight") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun GoalSelection(goal: String, onGoalChanged: (String) -> Unit) {
    Text(
        text = "What is your goal?",
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )

    Text(
        text = goal,
        fontSize = 32.sp,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GoalButton("Gaining muscle", onGoalChanged)
        Spacer(modifier = Modifier.width(16.dp))
        GoalButton("Losing weight", onGoalChanged)
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun GoalButton(text: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(text) },
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun WorkoutDaysSelection(selectedDays: List<DaysOfWeek>, onSelectedDaysChanged: (List<DaysOfWeek>) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Select Workout Days",
            modifier = Modifier.padding(16.dp)
        )

        DaysOfWeek.values().forEachIndexed { index, day ->
            WorkoutDayItem(selectedDays.contains(day), day) { newCheckedState ->
                val updatedSelectedDays = if (newCheckedState) {
                    selectedDays + day // Add the day to the selected list
                } else {
                    selectedDays - day // Remove the day from the selected list
                }
                onSelectedDaysChanged(updatedSelectedDays)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun WorkoutDayItem(isChecked: Boolean, day: DaysOfWeek, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { newCheckedState ->
                onCheckedChange(newCheckedState)
            },
            colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = day.name)
    }
}

@Composable
fun SignUpButton(
    userViewModel: UserViewModel,
    email: String,
    name: String,
    gender: String,
    age: Int,
    height: Int,
    weight: Int,
    goal: String,
    selectedDays: List<DaysOfWeek>,
    navController: NavController
) {
    Button(
        onClick = {
            // Handle registration here. You can save the data to your ViewModel or perform
            // the registration API call here.
            userViewModel.email = email
            userViewModel.name = name
            userViewModel.gender = gender
            userViewModel.age = age
            userViewModel.height = height
            userViewModel.weight = weight
            userViewModel.goal = goal
            userViewModel.day = selectedDays
            navController.navigate(GymmateRoute.CONFIRMATION)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = "Sign Up")
    }
}

enum class DaysOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}
*/