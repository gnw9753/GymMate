package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.gymmate.questionpage.QuestionPageViewModel

@Composable
fun GenderPage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Note to refactor this into cleaner code
            Button(
                onClick = {
                    if (!viewModel.hasSelectGender) {
                        viewModel.hasSelectGender = true
                        viewModel.male = true
                    }
                    else if(viewModel.hasSelectGender && viewModel.male) {
                        viewModel.hasSelectGender = false
                        viewModel.male = false
                    }
                },
                enabled = (!viewModel.female && !viewModel.other),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
            ) {
                Text("Male")
            }
            Button(
                onClick = {
                    if (!viewModel.hasSelectGender) {
                        viewModel.hasSelectGender = true
                        viewModel.female = true
                    }
                    else if(viewModel.hasSelectGender && viewModel.female) {
                        viewModel.hasSelectGender = false
                        viewModel.female = false
                    }
                },
                enabled = (!viewModel.male && !viewModel.other),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
            ) {
                Text("Female")
            }
            Button(
                onClick = {
                    if (!viewModel.hasSelectGender) {
                        viewModel.hasSelectGender = true
                        viewModel.other = true
                    }
                    else if(viewModel.hasSelectGender && viewModel.other) {
                        viewModel.hasSelectGender = false
                        viewModel.other = false
                    }
                },
                enabled = (!viewModel.female && !viewModel.male),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
            ) {
                Text("Burger")
            }
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
