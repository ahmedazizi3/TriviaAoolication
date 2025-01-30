package azizi.ahmed.mytrivia.packages.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import azizi.ahmed.mytrivia.packages.data.DataOrException
import azizi.ahmed.mytrivia.packages.model.QuestionItem
import azizi.ahmed.mytrivia.packages.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

//    init {
//        getAllQuestions()
//    }

    fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
    }

    fun getRaceModeQuestions() {
        viewModelScope.launch {
            val result = repository.getAllQuestions() // Fetch all questions from the API
            if (result.data != null) {
                // Shuffle the questions and take the first 10
                val randomQuestions = result.data!!.shuffled().take(10)
                data.value = DataOrException(ArrayList(randomQuestions), false, null)
            }
        }
    }

    fun onAnswerSelected(questionIndex: Int, onNextClicked: (Int) -> Unit) {
        viewModelScope.launch {
            delay(1500) // ✅ Delay inside ViewModel
            onNextClicked(questionIndex) // ✅ Move to next question
        }
    }
}