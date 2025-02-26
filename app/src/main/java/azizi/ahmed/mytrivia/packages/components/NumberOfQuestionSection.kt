package azizi.ahmed.mytrivia.packages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
//            .fillMaxWidth(0.5f)
            .background(backgroundColor)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Question:  $questionNumber/$totalQuestionsNumber",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
