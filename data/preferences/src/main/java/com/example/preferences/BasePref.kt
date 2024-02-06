package com.example.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.data.preferences.R

open class BasePref(
    context: Context,
) {
    val pref: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.pref_key), Context.MODE_PRIVATE)
}