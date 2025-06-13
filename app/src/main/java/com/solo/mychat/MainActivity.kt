package com.solo.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.solo.mychat.presentation.navigation.AppNavigator
import com.solo.mychat.presentation.navigation.BottomNavigationItems
import com.solo.mychat.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                val navigationItems = listOf(
                    BottomNavigationItems(
                        title = "Goal",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home
                    ),
                    BottomNavigationItems(
                        title = "Chat",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email
                    ),
                    BottomNavigationItems(
                        title = "Stats",
                        selectedIcon = Icons.Filled.DateRange,
                        unselectedIcon = Icons.Outlined.DateRange
                    )
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
                    var selectedIndex by remember {
                        mutableStateOf(0)
                    }
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehaviour.nestedScrollConnection),
                        topBar = {
                            val title = when (selectedIndex) {
                                0 -> "My goals"
                                1 -> "My chat"
                                2 -> "My stats"
                                else -> ""
                            }
                            TopAppBar(
                                title = { Text(text = title) },
//                                actions = {
//                                    IconButton(onClick = { /*TODO*/ }) {
//                                        Icon(
//                                            imageVector = Icons.Default.Menu,
//                                            contentDescription = "Filter Icon"
//                                        )
//                                    }
//                                },
                                scrollBehavior = scrollBehaviour
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                navigationItems.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedIndex == index,
                                        onClick = {
                                            navController.navigate(item.title) {
                                                launchSingleTop = true
//                                                restoreState = false
                                                popUpTo(navController.graph.startDestinationId) {
                                                    inclusive = true
                                                }
                                            }
                                            selectedIndex = index
                                        },
                                        label = { Text(text = item.title) },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedIndex) item.selectedIcon else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    ) {
                        AppNavigator(
                            navController,
                            modifier = Modifier.padding(it)
                        )
                        BackHandler(
                            onBack = {
                                if (navController.currentDestination?.route == "Goal") {
                                    finish()
                                } else {
                                    navController.navigate("Goal") {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}