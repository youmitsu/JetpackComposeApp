package com.example.jetpackcomposeapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.repository.LaunchFlagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val launchFlagRepository: LaunchFlagRepository
) : ViewModel() {
    var onboardingCompleted: Boolean by mutableStateOf(false)
        private set

    fun loadOnInit() {
        onboardingCompleted = launchFlagRepository.isOnboardingCompleted()
    }

    fun setOnboardingCompleted() {
        onboardingCompleted = true
        launchFlagRepository.setOnboardingCompleted()
    }
}