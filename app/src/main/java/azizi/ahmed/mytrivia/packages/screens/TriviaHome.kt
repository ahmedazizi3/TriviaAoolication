package azizi.ahmed.mytrivia.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.mytrivia.packages.components.DottedLineSection
import azizi.ahmed.mytrivia.packages.components.NumberOfQuestionSection
import azizi.ahmed.mytrivia.packages.components.QuestionAndChoicesSection
import azizi.ahmed.mytrivia.packages.util.AppColors
import azizi.ahmed.mytrivia.packages.view_model.QuestionsViewModel


@Composable
fun TriviaHome(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f),
        0f
    )
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableStateOf(0)
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

            NumberOfQuestionSection(
                backgroundColor = AppColors.mDarkPurple,
                textColor = AppColors.mLightGray,
                questionNumber = questionIndex.value + 1,
                totalQuestionsNumber = questions?.size?.plus(1) ?: 0
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
                    questions?.get(questionIndex.value)
                } catch (ex: Exception) {
                    null
                }
                if (questions != null) {
                    QuestionAndChoicesSection(
                        question = question!!,
                        questionIndex = questionIndex,
                        viewModel = viewModel
                    ) {
                        questionIndex.value += 1
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TriviaHomePreview() {
    TriviaHome()
}