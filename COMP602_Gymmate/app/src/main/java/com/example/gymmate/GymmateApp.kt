package com.example.gymmate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymmate.homepage.Homepage
import com.google.android.material.bottomappbar.BottomAppBar


@Composable
fun GymmateApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GymmateNavHost(navController = navController, modifier = Modifier.weight(1f))
        GymmateNavigationBar(selectedDestination = GymmateRoute.HOME, navigateToTopLevelDestination = navigationActions::navigateTo)
    }
}

@Composable
private fun GymmateNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ) {
    NavHost(
        navController = navController,
        startDestination = GymmateRoute.HOME,
        modifier = modifier,
    ) {
        composable(GymmateRoute.HOME) {
            Homepage()
        }
    }
}

