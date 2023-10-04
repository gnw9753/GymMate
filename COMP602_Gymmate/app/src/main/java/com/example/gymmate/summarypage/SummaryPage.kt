package com.example.gymmate.summarypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.GymmateNavigationBar
import com.example.gymmate.GymmateRoute
import com.example.gymmate.NavigationActions
import com.example.gymmate.data.exercisedata.Exercise
import com.example.gymmate.ui.theme.Typography
import com.example.gymmatekotlin.BasicPieChart
import kotlinx.coroutines.launch

@Composable
fun SummaryPage(
    navigationActions: NavigationActions,
    viewModel: SummaryPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            WeightGraphCard(
            )
            CaloriesGraphCard(
            )
            BottomButton(
            )
        }

        GymmateNavigationBar(
            selectedDestination = GymmateRoute.SUMMARY,
            navigateToTopLevelDestination = navigationActions::navigateTo
        )

    }
}

@Composable
fun WeightGraphCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Weight graph")
    }
}

@Composable
fun CaloriesGraphCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("calories graph")
    }
}

@Composable
fun BottomButton(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopRowButton()
        BottomRowButton()
    }
}

@Composable
fun TopRowButton(modifier: Modifier = Modifier) {
    Row {
        Button(
            onClick = {}
        ) {
            Text("Change Workout")
        }
        Button(
            onClick = {}
        ) {
            Text("Download Workout")
        }
    }
}

@Composable
fun BottomRowButton(modifier: Modifier = Modifier) {
    Row {
        Button(
            onClick = {}
        ) {
            Text("Change User Info")
        }
        Button(
            onClick = {}
        ) {
            Text("Download Workout")
        }
    }
}
