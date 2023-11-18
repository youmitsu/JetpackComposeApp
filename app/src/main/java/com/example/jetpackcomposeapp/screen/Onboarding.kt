package com.example.jetpackcomposeapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.Route
import com.example.jetpackcomposeapp.ui.theme.BaseAppTheme

@Composable
fun Onboarding(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("名言リマインダーへようこそ")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { navController.navigate(Route.MEIGEN_LIST.name) }
        ) {
            Text("続ける")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    BaseAppTheme {
        Onboarding(navController)
    }
}
