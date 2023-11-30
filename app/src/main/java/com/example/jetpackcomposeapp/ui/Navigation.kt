package com.example.jetpackcomposeapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.example.jetpackcomposeapp.bottomNavItems
import com.example.jetpackcomposeapp.screen.ReminderSetting
import com.example.jetpackcomposeapp.screen.Screen

@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { item ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == item.screen.route
                        } == true,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
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
            navController = navController,
            startDestination = Screen.MeigenList.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.MeigenList.route) {
                ListRoute()
            }
            composable(route = Screen.ReminderSetting.route) {
                ReminderSetting()
            }
        }
    }
}