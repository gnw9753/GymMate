package com.example.gymmate.homepage

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.data.exercisedata.ExerciseDay
import com.example.gymmate.ui.theme.Typography

@Composable
fun DateCardRow(day: String, exerciseDay: ExerciseDay, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(5.dp)

    ) {
        DayCard(day)
        ExerciseCard(exerciseDay.isAvailable, exerciseDay.exerciseList)
    }
}

@Composable
fun DayCard(day: String, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier
                .height(100.dp)
                .padding(8.dp)
        ) {
            Text(
                text = day,
                style = Typography.headlineMedium,

                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCard(
    isAvailable: Boolean,
    exerciseList: List<Exercise>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // Calls the function to grab exercises to be printed out
    var exerciseString: String = exerciseListOutput(isAvailable, exerciseList)

    // Start of UI
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .padding(start = 5.dp)
            .fillMaxWidth(),
        onClick = { expanded = !expanded },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {

            ExerciseCardText(isAvailable, expanded, exerciseString)
            if (isAvailable) {
                ExpandIcon(expanded)
            }
        }
    }
}

fun exerciseListOutput(isAvailable: Boolean, exerciseList: List<Exercise>): String {
    var exerciseString: String = ""

    if (isAvailable) {
        for (item in exerciseList) {
            item?.let {
                exerciseString += item.exerciseName + "\n"
            }
        }
    } else {
        exerciseString = "Nothing today! \nRemember to rest well!"
    }
    return exerciseString
}

@Composable
fun ExpandIcon(expanded: Boolean, modifier: Modifier = Modifier) {
    Icon(
        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(start = 0.dp, end = 0.dp, bottom = 0.dp, top = 8.dp)
            .background(color = Color(0x10006C4C))
            .height(25.dp)
    )
}

@Composable
fun ExerciseCardText(
    isAvailable: Boolean,
    expanded: Boolean,
    exerciseListString: String,
    modifier: Modifier = Modifier
) {
    Column{
        if (expanded && isAvailable) {
            Text(
                text = exerciseListString,
                modifier = Modifier
                    .padding(top = 8.dp, start = 10.dp, end = 10.dp, bottom = 8.dp)
            )
        } else {
            Text(
                text = exerciseListString,
                modifier = Modifier
                    .padding(top = 8.dp, start = 10.dp, end = 10.dp, bottom = 8.dp)
                    .height(if (isAvailable) 50.dp else 84.dp)
            )
        }

    }

}
