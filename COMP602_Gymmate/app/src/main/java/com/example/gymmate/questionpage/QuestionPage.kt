package com.example.gymmate.questionpage

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmate.AppViewModelProvider
import com.example.gymmate.questionpage.questions.AgePage
import com.example.gymmate.questionpage.questions.ConfirmationPage
import com.example.gymmate.questionpage.questions.DayPage
import com.example.gymmate.questionpage.questions.EmailPage
import com.example.gymmate.questionpage.questions.GenderPage
import com.example.gymmate.questionpage.questions.GoalPage
import com.example.gymmate.questionpage.questions.HeightPage
import com.example.gymmate.questionpage.questions.LoadingPage
import com.example.gymmate.questionpage.questions.NamePage
import com.example.gymmate.questionpage.questions.WeightPage
import com.example.gymmate.ui.theme.Typography


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuestionPage(
    viewModel: QuestionPageViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToHomePage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val questionPageUiState by viewModel.uiState.collectAsState()
    val animatedProgress by animateFloatAsState(
        targetValue = questionPageUiState.progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
    )
    if(questionPageUiState.pageIndex > 9){
        LoadingPage(viewModel, navigateToHomePage)
    }
    else{

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {

            TextButton(
                onClick = {
                    viewModel.decreasePageIndex()
                },
                modifier = Modifier
                    .align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                ) {
                    // THE NESTING IS CRAZY
                    DisplayAmountLeft(questionPageUiState.pageIndex)
                    TopLinearProgressIndicator(animatedProgress = animatedProgress)
                    AnimatedContent(
                        targetState = questionPageUiState.pageIndex,
                        transitionSpec = {
                            // Compare the incoming number with the previous number.
                            if (targetState > initialState) {
                                // If the target number is larger, it slides up and fades in
                                // while the initial (smaller) number slides up and fades out.
                                (slideInVertically { height -> height } + fadeIn()).togetherWith(
                                    slideOutVertically { height -> -height } + fadeOut())
                            } else {
                                // If the target number is smaller, it slides down and fades in
                                // while the initial number slides down and fades out.
                                (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                                    slideOutVertically { height -> height } + fadeOut())
                            }.using(
                                // Disable clipping since the faded slide-in/out should
                                // be displayed out of bounds.
                                SizeTransform(clip = false)
                            )
                        }, label = ""
                    ) { targetCount ->
                        CallQuestionPages(index = targetCount, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayAmountLeft(pageIndex: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "$pageIndex",
            style = Typography.headlineMedium,
            textAlign = TextAlign.Start
        )
        Text(
            text = "of 9",
            style = Typography.bodySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 15.dp, start = 4.dp)
                .alpha(0.7f)
        )
    }
}

@Composable
fun TopLinearProgressIndicator(
    animatedProgress: Float,
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        progress = animatedProgress,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CallQuestionPages(index: Int, viewModel: QuestionPageViewModel) {
    when (index) {
        1 -> {
            NamePage(viewModel)
        }

        2 -> {
            EmailPage(viewModel)
        }

        3 -> {
            AgePage(viewModel)
        }

        4 -> {
            GenderPage(viewModel)
        }

        5 -> {
            GoalPage(viewModel)
        }

        6 -> {
            HeightPage(viewModel)
        }

        7 -> {
            WeightPage(viewModel)
        }

        8 -> {
            DayPage(viewModel)
        }

        9 -> {
            ConfirmationPage(viewModel)
        }

        else -> {
            LoadingPage(viewModel, navigateToInitializeScreen = { /*TODO*/ })
        }
    }
}

/*
val context = LocalContext.current
val coroutineScope = rememberCoroutineScope()
Button(
    onClick = {
        coroutineScope.launch {
            //viewModel.insertExercise(context)
            onNavigateUp()
        }
    }
){
    Text(
        text = "Confirm"
    )
}*/