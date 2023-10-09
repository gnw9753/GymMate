package com.example.gymmate.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gymmate.ui.theme.Typography

@Composable
fun DayDisplayTopBar(
    viewModel: HomepageViewModel,
    modifier: Modifier = Modifier
) {
    val listOfDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    OutlinedCard(){
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(listOfDays) { day ->
                DayCard(day, false)
            }
        }
    }

}


@Composable
fun DayCard(
    day: String,
    didLogin: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxHeight()
            .width(50.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Text(
                text = day,
                style = Typography.titleSmall,
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )
            Icon(
                imageVector = if(didLogin)Icons.Default.TaskAlt else Icons.Default.RadioButtonUnchecked,
                contentDescription = null
            )

        }
    }
}