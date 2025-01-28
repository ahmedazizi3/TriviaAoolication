package azizi.ahmed.mytrivia.packages.di

import azizi.ahmed.mytrivia.packages.network.QuestionAPI
import azizi.ahmed.mytrivia.packages.repository.QuestionRepository
import azizi.ahmed.mytrivia.packages.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionRepository(questionAPI: QuestionAPI) = QuestionRepository(questionAPI = questionAPI)


    @Singleton
    @Provides
    fun provideQuestionAPI(): QuestionAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionAPI::class.java)
    }
}