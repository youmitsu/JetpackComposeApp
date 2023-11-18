package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.screen.MeigenList
import com.example.jetpackcomposeapp.screen.Onboarding
import com.example.jetpackcomposeapp.ui.theme.BaseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

enum class Route {
    WELCOME,
    MEIGEN_LIST
}

@Composable
private fun MyApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Surface(modifier) {
        NavHost(
            navController = navController,
            startDestination = Route.WELCOME.name,
        ) {
            composable(route = Route.WELCOME.name) { Onboarding(navController = navController) }
            composable(route = Route.MEIGEN_LIST.name) { MeigenList() }
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    BaseAppTheme {
        MyApp(Modifier.fillMaxSize())
    }
}