package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.gymmate.questionpage.QuestionPageViewModel

@Composable
fun GoalPage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
    ) {
        Button(
            onClick = {
                if (!viewModel.hasSelectGoal) {
                    viewModel.hasSelectGoal = true
                    viewModel.loseWeight = true
                } else if (viewModel.hasSelectGoal && viewModel.loseWeight) {
                    viewModel.hasSelectGoal = false
                    viewModel.loseWeight = false
                }
            },
            enabled = (!viewModel.gainMuscle),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Text("Lose Weight")
        }
        /* Implement at another time
        Button(onClick = {},
            ){
            Text("Maintain")
        }*/
        Button(
            onClick = {
                if (!viewModel.hasSelectGoal) {
                    viewModel.hasSelectGoal = true
                    viewModel.gainMuscle = true
                } else if (viewModel.hasSelectGoal && viewModel.gainMuscle) {
                    viewModel.hasSelectGoal = false
                    viewModel.gainMuscle = false
                }
            },
            enabled = (!viewModel.loseWeight),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
        ) {
            Text("Gain Muscle")
        }
        TextButton(
            onClick = {
                //if(hasSelect)
                viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Next")

        }
    }
}