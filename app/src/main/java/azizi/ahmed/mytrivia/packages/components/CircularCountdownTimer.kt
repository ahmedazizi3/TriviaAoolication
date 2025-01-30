package azizi.ahmed.mytrivia.packages.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import azizi.ahmed.mytrivia.packages.util.AppColors
import kotlinx.coroutines.delay

@Composable
fun CircularCountdownTimer(
    modifier: Modifier = Modifier,
    totalTime: Int, onTimerEnd: () -> Unit
) {
    var timeLeft by remember { mutableIntStateOf(totalTime) }
    val progress by animateFloatAsState(
        targetValue = timeLeft / totalTime.toFloat(),
        animationSpec = tween(durationMillis = 1000),
        label = "Progress Animation"
    )

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else {
            onTimerEnd()
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(70.dp)) {
        // Circular Progress Indicator
        Canvas(modifier = Modifier.size(70.dp)) {
            drawArc(
                color = Color.Gray,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = progress * 360f,
                useCenter = false,
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Timer Text
        Text(
            text = "$timeLeft s",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.mOffWhite
        )
    }
}
