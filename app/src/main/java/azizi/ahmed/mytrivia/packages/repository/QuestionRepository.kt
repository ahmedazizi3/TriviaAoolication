package azizi.ahmed.mytrivia.packages.repository

import azizi.ahmed.mytrivia.packages.data.DataOrException
import azizi.ahmed.mytrivia.packages.model.QuestionItem
import azizi.ahmed.mytrivia.packages.network.QuestionAPI
import javax.inject.Inject
import java.lang.Exception

class QuestionRepository @Inject constructor(private val questionAPI: QuestionAPI) {
    private val repositoryDataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            repositoryDataOrException.loading = true
            repositoryDataOrException.data = questionAPI.getAllQuestions()
            if (repositoryDataOrException.data.toString().isNotBlank()) repositoryDataOrException.loading = false
        } catch (exception: Exception) {
            repositoryDataOrException.exception = exception
        }
        return repositoryDataOrException
    }
}