package com.example.gymmate.questionpage.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gymmate.questionpage.QuestionPageViewModel
import com.example.gymmate.ui.theme.Typography

@Composable
fun ConfirmationPage(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 5.dp)
    ) {
        EditTextRow(
            title = "Name",
            displayText = viewModel.name,
            viewModel,
            page = 1
        )
        Divider()
        EditTextRow(
            title = "Email",
            displayText = viewModel.email,
            viewModel,
            page = 2
        )
        Divider()
        EditTextRow(
            title = "Age",
            displayText = viewModel.age,
            viewModel,
            page = 3
        )
        Divider()
        EditTextRow(
            title = "Gender",
            displayText = viewModel.gender,
            viewModel = viewModel,
            page = 4
        )
        Divider()
        EditTextRow(
            title = "Goal",
            displayText = viewModel.goal,
            viewModel = viewModel,
            page = 5
        )
        Divider()
        EditTextRow(
            title = "Height",
            displayText = viewModel.height,
            viewModel = viewModel,
            page = 6
        )
        Divider()
        EditTextRow(
            title = "Weight",
            displayText = viewModel.weight,
            viewModel = viewModel,
            page = 7
        )
        Divider()
        DisplayDay(viewModel)
        TextButton(
            onClick = {
                //if(viewModel.validate())
                    viewModel.increasePageIndex()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Confirm")

        }
    }
}

@Composable
fun EditTextRow(title: String, displayText: String, viewModel: QuestionPageViewModel, page: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 7.dp)
    ) {
        Column(
        ) {
            Text(
                text = title,
                style = Typography.headlineSmall,
            )
            Text(
                text = displayText,
                style = Typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = {
            viewModel.setPageIndex(page)
        }) {
            Text("edit")
        }
    }
}

@Composable
fun DisplayDay(viewModel: QuestionPageViewModel, modifier: Modifier = Modifier) {
    val dayList = listOf(
        viewModel.monday,
        viewModel.tuesday,
        viewModel.wednesday,
        viewModel.thursday,
        viewModel.friday,
        viewModel.saturday,
        viewModel.sunday
    )

    val daysOfWeek = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    var tempList: MutableList<String> = mutableListOf()

    for (index in dayList.indices) {
        if (dayList[index]) {
            tempList.add(daysOfWeek[index])
        }
    }

    Column(
    ) {
        Text(
            text = "Days available",
            style = Typography.headlineSmall
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
        ) {
            items(tempList.size) { index ->
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = daysOfWeek[index],
                            textAlign = TextAlign.Center
                        )
                    },
                )

            }
        }
    }
}
