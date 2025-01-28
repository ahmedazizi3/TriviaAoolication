package azizi.ahmed.mytrivia.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberOfQuestionSection(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color,
    questionNumber: Int = 15,
    totalQuestionsNumber: Int = 110
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Question  $questionNumber/",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = textColor
        )


        Text(
            text = "$totalQuestionsNumber",
            fontSize = 20.sp,
            modifier = modifier.align(Alignment.Bottom),
            color = textColor
        )

    }
}

@Preview
@Composable
private fun TopSectionPreview() {
    NumberOfQuestionSection(
        backgroundColor = Color.Gray,
        textColor = Color.White
    )
}