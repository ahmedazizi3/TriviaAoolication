package azizi.ahmed.mytrivia.packages.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.mytrivia.packages.components.CircularCountdownTimer
import azizi.ahmed.mytrivia.packages.components.DottedLineSection
import azizi.ahmed.mytrivia.packages.components.NumberOfQuestionSection
import azizi.ahmed.mytrivia.packages.components.QuestionAndChoicesSection
import azizi.ahmed.mytrivia.packages.components.ScreenTitleSection
import azizi.ahmed.mytrivia.packages.util.AppColors
import azizi.ahmed.mytrivia.packages.viewModel.QuestionsViewModel


@Composable
fun RaceTimeGameScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel = hiltViewModel(),
    navigateToStartingScreen: () -> Unit = {}
) {
    // Call getRaceModeQuestions to fetch 10 random questions when the screen is launched
    LaunchedEffect(Unit) {
        viewModel.getRaceModeQuestions() // Fetch 10 random questions
    }

    // Get the shuffled 10 random questions
    val questions = viewModel.viewModelDataOrException.value.data?.toMutableList() ?: emptyList()
    val questionIndex = remember { mutableIntStateOf(0) }
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f),
        0f
    )
    val context = LocalContext.current
    val score = remember {
        mutableIntStateOf(0)
    }



    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.mDarkPurple)
            .padding(4.dp),
        color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(AppColors.mDarkPurple),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = modifier.padding(20.dp))

            ScreenTitleSection(title = "Race Time Game")

            Spacer(modifier = modifier.padding(10.dp))

            DottedLineSection(
                pathEffect = pathEffect
            )

            Spacer(modifier = modifier.padding(5.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberOfQuestionSection(
                    backgroundColor = AppColors.mDarkPurple,
                    textColor = AppColors.mLightGray,
                    questionNumber = questionIndex.intValue + 1,
                    totalQuestionsNumber = questions.size
                )
                val question = questions.getOrNull(questionIndex.intValue)
                if (question != null) {
                    CircularCountdownTimer(
                        totalTime = 60
                    ) {
//                    Handle timer end
                        navigateToStartingScreen()
                        Toast.makeText(context, "Your score is ${score.intValue}/10", Toast.LENGTH_SHORT).show()
                    }
                }


                Spacer(modifier = modifier.width(10.dp))
            }


            Spacer(modifier = modifier.padding(5.dp))

            DottedLineSection(
                pathEffect = pathEffect
            )

            Spacer(modifier = modifier.padding(10.dp))

            if (viewModel.viewModelDataOrException.value.loading == true) {
                CircularProgressIndicator(color = AppColors.mOffWhite)
            } else {
                val question = questions.getOrNull(questionIndex.intValue)
                if (question != null) {
                    QuestionAndChoicesSection(
                        question = question,
                        questionIndex = questionIndex,
                        viewModel = viewModel,
                        addToScore = {
                            score.intValue++
                        }
                    ) {
                        // Proceed to next question
                        if (questionIndex.intValue + 1 >= questions.size) {
                            navigateToStartingScreen()
                            Toast.makeText(context, "Your score is ${score.intValue}/10", Toast.LENGTH_SHORT).show()
                        } else {
                            questionIndex.intValue++
                        }
                    }
                }
            }
        }
    }
}


