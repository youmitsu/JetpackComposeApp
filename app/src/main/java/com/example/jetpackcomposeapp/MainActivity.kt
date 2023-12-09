package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.ui.MainNavigation
import com.example.onboarding.ui.Onboarding
import com.example.ui.theme.BaseAppTheme
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
