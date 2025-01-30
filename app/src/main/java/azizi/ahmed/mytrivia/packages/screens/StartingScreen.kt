package azizi.ahmed.mytrivia.packages.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.mytrivia.packages.util.AppColors

@Composable
fun StartingScreen(
    modifier: Modifier = Modifier,
    navigateToClassicGame : () -> Unit,
    navigateToRaceTimeGame : () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.mDarkPurple)
            .padding(6.dp),
        color = AppColors.mDarkPurple
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(AppColors.mDarkPurple),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = modifier.height(25.dp))
            Text(
                text = "Trivia",
                color = AppColors.mOffWhite,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top = 20.dp, bottom = 50.dp)
            )

            Spacer(modifier = modifier.height(200.dp))

            Text(
                text = "How do you wanna play?",
                color = AppColors.mOffWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top = 20.dp)
            )

            Spacer(modifier = modifier.height(40.dp))

            Button(
                onClick = {
                    navigateToClassicGame()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.mLightBlue,
                    contentColor = AppColors.mOffWhite
                ),
                modifier = modifier.width(300.dp).height(60.dp)
            ) {
                Text(
                    text = "Classic",
                    fontSize = 30.sp,
                )
            }

            Spacer(modifier = modifier.height(10.dp))


//            TODO: Taking care of the race with time screen later ...
            Button(
                onClick = {
                    navigateToRaceTimeGame()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.mLightBlue,
                    contentColor = AppColors.mOffWhite
                ),
                modifier = modifier.width(300.dp).height(60.dp)
            ) {
                Text(
                    text = "Race Time",
                    fontSize = 30.sp,
                )
            }

            Text(
                text = "BY Ahmed Azizi",
                modifier = modifier
                    .padding(top = 270.dp, end = 20.dp)
                    .align(Alignment.End),
                fontStyle = FontStyle.Italic,
            )
        }
    }
}

//@Preview
//@Composable
//private fun StartingScreenPreview() {
//    StartingScreen(
//        navigateToClassicGame = {},
//        navigateToRaceTimeGame = {}
//    )
//}