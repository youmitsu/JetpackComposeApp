package com.example.preferences

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface LaunchPref {
    fun isOnboardingCompleted(): Boolean
    fun setOnboardingCompleted(isOnboardingCompleted: Boolean)
}

class LaunchPrefImpl @Inject constructor(
    @ApplicationContext context: Context,
) : BasePref(context), LaunchPref {
    override fun isOnboardingCompleted(): Boolean {
        return pref.getBoolean(IsOnboardingCompletedKey, false)
    }

    override fun setOnboardingCompleted(isOnboardingCompleted: Boolean) {
        pref.edit {
            putBoolean(IsOnboardingCompletedKey, isOnboardingCompleted)
        }
    }

    companion object {
        const val IsOnboardingCompletedKey = "onboarding_completed"
    }
}