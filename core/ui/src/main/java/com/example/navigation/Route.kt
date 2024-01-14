package com.example.navigation

sealed class Screen(val route: String, val argumentName: String = "") {

    val routeName: String = if (argumentName.isEmpty()) {
        route
    } else {
        "$route/{$argumentName}"
    }

    fun createRoute(argument: String = ""): String {
        return if (argumentName.isEmpty()) {
            route
        } else {
            if (argument.isEmpty()) {
                throw IllegalArgumentException("Argument must not be empty")
            }
            "$route/$argument"
        }
    }

    object Welcome : Screen("/welcome")

    object Home : Screen("/")

    object Register : Screen("/register")
    object Edit : Screen("/meigen", "meigenId")
}

sealed class BottomNavScreen(val route: String) {
    object MeigenList : BottomNavScreen("list")
    object ReminderSetting : BottomNavScreen("reminder_setting")
}
