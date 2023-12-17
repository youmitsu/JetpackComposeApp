package com.example.jetpackcomposeapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.feature.list.ui.ListRoute
import com.example.jetpackcomposeapp.R
import com.example.jetpackcomposeapp.screen.BottomNavScreen
import com.example.jetpackcomposeapp.screen.Screen
import com.example.registration.RegistrationRoute
import com.example.reminder.ui.ReminderRoute

sealed class BottomNavItem(
    val screen: BottomNavScreen,
    val icon: ImageVector
) {
    object HomeTab : BottomNavItem(BottomNavScreen.MeigenList, Icons.Filled.Home)

    object RemindSettingTab : BottomNavItem(BottomNavScreen.ReminderSetting, Icons.Filled.Alarm)

    companion object {
        val items = listOf(
            HomeTab,
            RemindSettingTab,
        )
    }
}

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    val bottomNavController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        navController.navigate(Screen.Register.route)
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "追加")
                    }
                },
                bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        BottomNavItem.items.forEach { item ->
                            BottomNavigationItem(
                                selected = currentDestination?.hierarchy?.any {
                                    it.route == item.screen.route
                                } == true,
                                onClick = {
                                    bottomNavController.navigate(item.screen.route) {
                                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = { Icon(item.icon, contentDescription = null) })
                        }
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(id = R.string.app_name))
                        }
                    )
                }
            ) {
                NavHost(
                    navController = bottomNavController,
                    startDestination = BottomNavScreen.MeigenList.route,
                    modifier = Modifier.padding(it)
                ) {
                    composable(BottomNavScreen.MeigenList.route) {
                        ListRoute()
                    }
                    composable(route = BottomNavScreen.ReminderSetting.route) {
                        ReminderRoute()
                    }
                }
            }
        }
        composable(Screen.Register.route) {
            RegistrationRoute(navController)
        }
    }
}