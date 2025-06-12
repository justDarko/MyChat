package com.solo.mychat.presentation.screen.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.mychat.presentation.components.GoalsCircularProgressBar

@Composable
fun StatsScreen(
    modifier: Modifier,
    statsViewModel: StatsViewModel = hiltViewModel()
) {
    val state = statsViewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.value.numberOfGoals != 0 && state.value.numberOfAchievedGoals != 0) {
            val goalAccomplishmentsPercentage =
                (state.value.numberOfAchievedGoals.toFloat() / state.value.numberOfGoals.toFloat()) * 100
            val titleText = if (goalAccomplishmentsPercentage < 50)
                "You need to focus more on your goals in the future!"
            else
                "You are making excellent progress. Keep up with the work!"

            Text(
                text = titleText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            GoalsCircularProgressBar(
                totalGoals = state.value.numberOfGoals,
                achievedGoals = state.value.numberOfAchievedGoals,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Center it
            )
        }
    }
}