package azizi.ahmed.mytrivia.packages.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import azizi.ahmed.mytrivia.packages.screens.StartingScreen
import azizi.ahmed.mytrivia.packages.screens.ClassicGameScreen
import azizi.ahmed.mytrivia.packages.screens.RaceTimeGameScreen

@Composable
fun TriviaNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = TriviaScreens.StartingScreen.route
    ) {
        composable(
            route = TriviaScreens.StartingScreen.route
        ) {
            StartingScreen(
                navigateToClassicGame = {
                    navController.navigate(TriviaScreens.ClassicGameScreen.route)
                },
                navigateToRaceTimeGame = {
                    navController.navigate(TriviaScreens.RaceTimeGameScreen.route)
                }
            )
        }

        composable(
            route = TriviaScreens.ClassicGameScreen.route
        ) {
            ClassicGameScreen()
        }

        composable(
            route = TriviaScreens.RaceTimeGameScreen.route
        ) {
            RaceTimeGameScreen {
                navController.popBackStack()
            }
        }
    }
}