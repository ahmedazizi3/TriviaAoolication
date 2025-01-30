package azizi.ahmed.mytrivia.packages.navigation

sealed class TriviaScreens (
    val route : String
) {
    object SplashScreen : TriviaScreens("splashScreen")
    object StartingScreen : TriviaScreens("startingScreen")
    object ClassicGameScreen : TriviaScreens("classicGameScreen")
    object RaceTimeGameScreen : TriviaScreens("raceTimeGameScreen")
}