package com.solo.mychat.presentation.screen.goal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.mychat.presentation.components.AddGoalDialog
import com.solo.mychat.presentation.components.GoalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalScreen(
    modifier: Modifier,
    goalsViewModel: GoalsViewModel = hiltViewModel()
) {
    val state = goalsViewModel.state.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(text = "My goals") },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Filter Icon"
                            )
                        }
                    },
                    scrollBehavior = scrollBehaviour
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        goalsViewModel.onAction(GoalsActions.OpenAddGoalDialog(open = true))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add goal"
                    )
                }
            }
        ) { values ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
            ) {
                items(state.value.listOfGoals, key = { item -> item.id }) {
                    GoalItem(
                        goalTitle = it.goalTitle,
                        goalStatus = if (it.achieved) "Achieved" else "Not achieved",
                        icon = if (it.achieved) Icons.Default.Check else Icons.Default.Clear,
                        onCompleteClick = {
                            goalsViewModel.onAction(GoalsActions.CompleteGoal(goal = it))
                        },
                        onDeleteClick = {
                            goalsViewModel.onAction(GoalsActions.DeleteGoal(goal = it))
                        }
                    )
                }
            }
        }
        if (state.value.showAddGoalDialog) {
            AddGoalDialog(
                modifier = modifier,
                onDismiss = { goalsViewModel.onAction(GoalsActions.OpenAddGoalDialog(open = false)) },
                onAction = { goalTitle, isAchieved ->
                    goalsViewModel.onAction(
                        GoalsActions.SaveGoal(
                            goalTitle = goalTitle,
                            isAchieved = isAchieved
                        )
                    ).also { goalsViewModel.onAction(GoalsActions.OpenAddGoalDialog(open = false)) }
                }
            )
        }
    }
}