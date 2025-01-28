package azizi.ahmed.mytrivia.packages.network

import azizi.ahmed.mytrivia.packages.model.Question
import azizi.ahmed.mytrivia.packages.model.QuestionItem
import retrofit2.Call
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface QuestionAPI {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}