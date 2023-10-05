package com.example.gymmate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymmate.caloriespage.CaloriesPage
import com.example.gymmate.homepage.Homepage
import com.example.gymmate.login.InitializeUserPage
import com.example.gymmate.login.LoginPage
import com.example.gymmate.questionpage.questions.EmailPage
import com.example.gymmate.questionpage.questions.NamePage
import com.example.gymmate.questionpage.QuestionPage
import com.example.gymmate.summarypage.SummaryPage
import com.google.android.material.bottomappbar.BottomAppBar


@Composable
fun GymmateApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        /*
        GymmateNavigationBar(
            selectedDestination = GymmateRoute.HOME,
            navigateToTopLevelDestination = navigationActions::navigateTo
        )*/

        GymmateNavHost(navController = navController, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun GymmateNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }
    NavHost(
        navController = navController,
        startDestination = GymmateRoute.LOGIN,
        modifier = modifier,
    ) {

        composable(route = GymmateRoute.LOGIN) {
            LoginPage(
                navigateToQuestion = { navController.navigate(GymmateRoute.QUESTION) },
                navigateToInitializeUser = { navController.navigate(GymmateRoute.INITIALIZEUSER) })
        }
        composable(route = GymmateRoute.QUESTION) {
            QuestionPage(navigateToHomePage = { navController.navigate(GymmateRoute.INITIALIZEUSER) })
        }
        composable(route = GymmateRoute.INITIALIZEUSER) {
            InitializeUserPage(navigateToHomePage = { navController.navigate(GymmateRoute.HOME) })
        }
        composable(route = GymmateRoute.HOME) {
            Homepage(navigationActions)
        }
        composable(route = GymmateRoute.CALORIES) {
            CaloriesPage(navigationActions)
        }
        composable(route = GymmateRoute.SUMMARY) {
            SummaryPage(navigationActions)
        }
    }
}

