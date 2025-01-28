package azizi.ahmed.mytrivia.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import azizi.ahmed.mytrivia.packages.components.NumberOfQuestionSection
import azizi.ahmed.mytrivia.packages.util.AppColors
import azizi.ahmed.mytrivia.packages.view_model.QuestionsViewModel


@Composable
fun TriviaHome(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
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
                textColor = AppColors.mLightGray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TriviaHomePreview() {
    TriviaHome()
}