package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.screen.Onboarding
import com.example.jetpackcomposeapp.screen.Screen
import com.example.jetpackcomposeapp.ui.MainNavigation
import com.example.jetpackcomposeapp.ui.theme.BaseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeigenApp()
        }
    }
}

sealed class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector
) {
    object HomeTab : BottomNavItem(Screen.MeigenList, Icons.Filled.Home)

    object RemindSettingTab : BottomNavItem(Screen.ReminderSetting, Icons.Filled.Alarm)
}

val bottomNavItems = listOf(
    BottomNavItem.HomeTab,
    BottomNavItem.RemindSettingTab,
)

@Composable
private fun MeigenApp(
    modifier: Modifier = Modifier,
) {
    var onboardingDisplayed by remember {
        mutableStateOf(false)
    }
    val navController = rememberNavController()

    BaseAppTheme {
        if (!onboardingDisplayed)
            Onboarding(navController = navController, onClick = {
                onboardingDisplayed = true
            })
        else
            MainNavigation()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    BaseAppTheme {
        MeigenApp(Modifier.fillMaxSize())
    }
}