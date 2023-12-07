package com.example.jetpackcomposeapp.screen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")

    object Home : Screen("home")

    object Register : Screen("register")
}

sealed class BottomNavScreen(val route: String) {
    object MeigenList : BottomNavScreen("list")
    object ReminderSetting: BottomNavScreen("reminder_setting")
}
