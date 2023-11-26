package com.example.jetpackcomposeapp.screen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object MeigenList : Screen("list")
    object ReminderSetting: Screen("reminder_setting")
}
