package com.solo.mychat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.solo.mychat.presentation.screen.chat.ChatScreen
import com.solo.mychat.presentation.screen.goal.GoalScreen
import com.solo.mychat.presentation.screen.stats.StatsScreen


@Composable
fun AppNavigator(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = "goal") {
        composable("Goal") {
            GoalScreen(modifier = modifier)
        }
        composable("Chat") {
            ChatScreen(modifier = modifier)
        }
        composable("Stats") {
            StatsScreen(modifier = modifier)
        }
    }
}