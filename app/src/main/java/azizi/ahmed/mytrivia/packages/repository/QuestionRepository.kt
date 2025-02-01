package azizi.ahmed.mytrivia.packages.repository

import azizi.ahmed.mytrivia.packages.data.DataOrException
import azizi.ahmed.mytrivia.packages.model.QuestionItem
import azizi.ahmed.mytrivia.packages.network.QuestionAPI
import javax.inject.Inject
import java.lang.Exception

class QuestionRepository @Inject constructor(private val questionAPI: QuestionAPI) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = questionAPI.getAllQuestions()
            if (dataOrException.data.toString().isNotBlank()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.exception = exception
        }
        return dataOrException
    }
}