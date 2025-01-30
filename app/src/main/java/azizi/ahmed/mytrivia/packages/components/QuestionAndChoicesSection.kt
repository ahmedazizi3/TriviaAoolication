package azizi.ahmed.mytrivia.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.mytrivia.packages.model.QuestionItem
import azizi.ahmed.mytrivia.packages.util.AppColors
import azizi.ahmed.mytrivia.packages.view_model.QuestionsViewModel

@Composable
fun QuestionAndChoicesSection(
    modifier: Modifier = Modifier,
    question: QuestionItem,
    questionIndex: MutableIntState,
    viewModel: QuestionsViewModel,
    addToScore: () -> Unit = {},
    onNextClicked: (Int) -> Unit = {}
) {
    val choicesState = remember(question) {
        question.choices.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    val isAnswered = remember {
        mutableStateOf(false)
    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        { it ->
            if (!isAnswered.value) {
                answerState.value = it
                correctAnswerState.value
                questionIndex.intValue.let { it1 ->
                    viewModel.onAnswerSelected(it1) {
                        answerState.value = null
                        correctAnswerState.value = null
                        isAnswered.value = false
                        onNextClicked(it)
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColors.mDarkPurple)
            .padding(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = question.question,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.mOffWhite,
            modifier = modifier
                .fillMaxHeight(0.4f)
        )

        // TODO: Choices
        choicesState.forEachIndexed { index, answerText ->
            Row(
                modifier = modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(
                        width = 4.dp, brush = Brush.linearGradient(
                            colors = listOf(
                                AppColors.mLightPurple, AppColors.mLightPurple
                            )
                        ),
                        shape = RoundedCornerShape(
                            15.dp
                        )
                    )
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 50,
                            topEndPercent = 50,
                            bottomEndPercent = 50,
                            bottomStartPercent = 50
                        )
                    )
                    .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (answerState.value == index),
                    onClick = {
                        updateAnswer(index)
                        if (correctAnswerState.value == true) {
                            addToScore()
                        }
                    },
                    enabled = !isAnswered.value,
                    modifier = modifier.padding(start = 16.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (correctAnswerState.value == true && index == answerState.value) {
                            Color.Green.copy(alpha = 0.2f)
                        } else {
                            Color.Red.copy(alpha = 0.2f)
                        }
                    )
                )
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Light,
                            color = if (correctAnswerState.value == true && index == answerState.value) {
                                Color.Green
                            } else if (correctAnswerState.value == false && index == answerState.value) {
                                Color.Red
                            } else {
                                AppColors.mOffWhite
                            },
                            fontSize = 17.sp
                        )
                    ) {
                        append(answerText)
                    }
                }
                Text(
                    text = annotatedString,
                    modifier = modifier.padding(6.dp)
                )
            }
        }

        Spacer(modifier = modifier.height(20.dp))
    }
}


