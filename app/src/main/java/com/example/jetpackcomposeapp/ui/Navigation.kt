package com.example.jetpackcomposeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposeapp.R
import com.example.meigen.create.MeigenCreatePageHost
import com.example.meigen.edit.EditRoute
import com.example.meigen.list.ListRoute
import com.example.navigation.BottomNavScreen
import com.example.navigation.Screen
import com.example.reminder.create.ReminderCreatePageHost
import com.example.reminder.edit.ReminderEditPageHost
import com.example.reminder.list.ReminderListPageHost

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
fun MainNavigation(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    val currentBottomTab by bottomNavController.currentBackStackEntryAsState()
    NavHost(navController = navController, startDestination = Screen.Home.routeName) {
        composable(Screen.Home.routeName) {
            Scaffold(
                floatingActionButton = {
                    when (currentBottomTab?.destination?.route) {
                        BottomNavScreen.MeigenList.route -> {
                            FloatingActionButton(onClick = {
                                navController.navigate(Screen.Register.routeName)
                            }) {
                                Icon(Icons.Filled.PostAdd, contentDescription = "追加")
                            }
                        }

                        BottomNavScreen.ReminderSetting.route -> {
                            FloatingActionButton(onClick = {
                                navController.navigate(Screen.ReminderCreate.routeName)
                            }) {
                                Icon(Icons.Filled.AddAlarm, contentDescription = "追加")
                            }
                        }
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
                        ListRoute(navController)
                    }
                    composable(route = BottomNavScreen.ReminderSetting.route) {
                        ReminderListPageHost(navController)
                    }
                }
            }
        }
        composable(Screen.Register.routeName) {
            MeigenCreatePageHost(navController)
        }
        composable(
            Screen.Edit.routeName, arguments = listOf(
                navArgument(Screen.Edit.argumentName) { type = NavType.StringType },
            )
        ) {
            val meigenId = it.arguments?.getString(Screen.Edit.argumentName)
            meigenId?.let { id ->
                EditRoute(id = id, navController = navController)
            } ?: throw IllegalArgumentException("meigenId is null")
        }
        composable(
            Screen.ReminderCreate.routeName
        ) {
            ReminderCreatePageHost(navController)
        }
        composable(
            Screen.ReminderEdit.routeName, arguments = listOf(
                navArgument(Screen.ReminderEdit.argumentName) { type = NavType.StringType },
            )
        ) {
            val reminderId = it.arguments?.getString(Screen.ReminderEdit.argumentName)
                ?: throw IllegalArgumentException("reminderId is null")
            ReminderEditPageHost(navController, reminderId = reminderId)
        }
    }
}