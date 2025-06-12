package com.solo.mychat.presentation.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GoalsCircularProgressBar(
    modifier: Modifier = Modifier,
    totalGoals: Int,
    achievedGoals: Int,
    strokeWidth: Dp = 16.dp,
    achievedColor: Color = Color(0xFF38E078),
    backgroundColor: Color = Color(0xFF264533)
) {
    val progress = achievedGoals.toFloat() / totalGoals.toFloat()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(300.dp)
            .height(300.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngleAchieved = 360 * progress
            val diameterOffset = strokeWidth.toPx() / 2
            val arcSize = Size(
                size.width - strokeWidth.toPx(),
                size.height - strokeWidth.toPx()
            )

            // Background arc - total goals
            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(diameterOffset, diameterOffset),
                size = arcSize,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // Foreground arc - achieved goals
            drawArc(
                color = achievedColor,
                startAngle = -90f,
                sweepAngle = sweepAngleAchieved,
                useCenter = false,
                topLeft = Offset(diameterOffset, diameterOffset),
                size = arcSize,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        // Optional: Text in the center
        Text(
            text = "Total: $totalGoals \n Achieved: $achievedGoals",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

