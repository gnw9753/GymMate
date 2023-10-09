package com.example.gymmate.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.GymmateNavigationBar
import com.example.gymmate.GymmateRoute
import com.example.gymmate.NavigationActions
import com.example.gymmate.data.userdata.UserInstance

@Composable
fun Homepage(
    navigationActions: NavigationActions,
    viewModel: HomepageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    var exerciseDayList = UserInstance.currentUser?.exercise_schedule
   Column(
       modifier = Modifier
           .fillMaxSize()
   ) {
       // Top horizontal slider // Carousel
       TopBarSwap(viewModel = viewModel, modifier = modifier.weight(0.25f))
       
       // Logic to decide to display homepage or exercise video page
       Divider(
           modifier = modifier
               .padding(5.dp)
       )

       LazyColumn(modifier = modifier
           .weight(1f)
       ) {
           items(exerciseDayList ?: emptyList()) { exercise ->
               DateCardRow(
                   day = exercise.day,
                   exerciseDay = exercise,
               )
           }
       }
       GymmateNavigationBar(
           selectedDestination = GymmateRoute.HOME,
           navigateToTopLevelDestination = navigationActions::navigateTo
       )
   }
}
