package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.navigation.MainNavigation
import com.example.jetpackcomposeapp.ui.MainViewModel
import com.example.jetpackcomposeapp.ui.rememberMainPageState
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
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()

    val pageState = rememberMainPageState(
        mainViewModel,
    )

    LaunchedEffect(Unit) {
        pageState.loadOnInit()
    }

    BaseAppTheme {
        if (!pageState.onboardingCompleted)
            Onboarding(
                navController = navController,
                onClick = pageState.onClickOnboardingComplete
            )
        else
            MainNavigation(navController)
    }
}
