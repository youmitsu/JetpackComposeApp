package com.example.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RegistrationRoute() {
    RegistrationScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("名言を登録")
            })
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            Text("Registration")
        }
    }
}