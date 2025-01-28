package azizi.ahmed.mytrivia.packages.components

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import azizi.ahmed.mytrivia.packages.view_model.QuestionsViewModel

@Composable
fun Questions(
    modifier: Modifier = Modifier,
    viewModel: QuestionsViewModel
) {
    val questions = viewModel.data.value.data?.toMutableList()

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
        Log.d("Loading", "Questions: Loading...")
    } else {
        Log.d("SIZE", "Questions: ${questions?.size}")
    }
}