package com.example.jetpackcomposeapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

data class MainPageState(
    val onboardingCompleted: Boolean,
    val onClickOnboardingComplete: () -> Unit,
    val loadOnInit: () -> Unit,
)

@Composable
fun rememberMainPageState(
    viewModel: MainViewModel
): MainPageState {
    val onboardingCompleted = viewModel.onboardingCompleted
    val onClickOnboardingComplete = viewModel::setOnboardingCompleted
    val loadOnInit = viewModel::loadOnInit

    return remember(
        onboardingCompleted,
        onClickOnboardingComplete,
        loadOnInit
    ) {
        MainPageState(
            onboardingCompleted = onboardingCompleted,
            onClickOnboardingComplete = onClickOnboardingComplete,
            loadOnInit = loadOnInit
        )
    }
}