package com.example.repository

import com.example.preferences.LaunchPref
import javax.inject.Inject

interface LaunchFlagRepository {
    fun isOnboardingCompleted(): Boolean
    fun setOnboardingCompleted()
}

class LaunchFlagRepositoryImpl @Inject constructor(
    private val launchPref: LaunchPref
) : LaunchFlagRepository {
    override fun isOnboardingCompleted(): Boolean {
        return launchPref.isOnboardingCompleted()
    }

    override fun setOnboardingCompleted() {
        launchPref.setOnboardingCompleted(true)
    }
}

