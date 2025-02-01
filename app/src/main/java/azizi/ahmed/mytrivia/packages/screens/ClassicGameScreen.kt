package azizi.ahmed.mytrivia.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.mytrivia.packages.components.DottedLineSection
import azizi.ahmed.mytrivia.packages.components.QuestionAndChoicesSection
import azizi.ahmed.mytrivia.packages.components.ScreenTitleSection
import azizi.ahmed.mytrivia.packages.util.AppColors
import azizi.ahmed.mytrivia.packages.view_model.QuestionsViewModel
import kotlin.random.Random


@Composable
fun ClassicGameScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllQuestions()
    }
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f),
        0f
    )
    val score = remember {
        mutableIntStateOf(0)
    }

    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
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

            ScreenTitleSection(
                title = "Classic Game"
            )

            Spacer(modifier = modifier.padding(10.dp))

            DottedLineSection(
                pathEffect = pathEffect
            )

            Spacer(modifier = modifier.padding(10.dp))

            if (viewModel.data.value.loading == true) {
                CircularProgressIndicator(
                    color = AppColors.mOffWhite
                )
            } else {
                val question = try {
                    questions?.get(questionIndex.intValue)
                } catch (ex: Exception) {
                    null
                }
                if (questions != null) {
                    QuestionAndChoicesSection(
                        question = question!!,
                        questionIndex = questionIndex,
                        viewModel = viewModel,
                        addToScore = {
                            score.intValue += 1
                        }
                    ) {
                        questionIndex.intValue = Random.nextInt(0, questions.size)
                    }

                    Spacer(modifier = modifier.height(10.dp))

                    Text(
                        text = "Score: ${score.intValue}",
                        color = AppColors.mOffWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                } else {
                    CircularProgressIndicator(
                        color = AppColors.mOffWhite
                    )
                }
            }
        }
    }
}