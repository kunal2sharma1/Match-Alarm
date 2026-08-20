package com.kunal.smartmathalarm

import android.content.Context

class AlarmStorage(context: Context) {
    private val prefs = context.getSharedPreferences("alarm_prefs", Context.MODE_PRIVATE)

    var enabled: Boolean
        get() = prefs.getBoolean("enabled", false)
        set(value) = prefs.edit().putBoolean("enabled", value).apply()

    var hour: Int
        get() = prefs.getInt("hour", 7)
        set(value) = prefs.edit().putInt("hour", value).apply()

    var minute: Int
        get() = prefs.getInt("minute", 0)
        set(value) = prefs.edit().putInt("minute", value).apply()

    var difficulty: Int
        get() = prefs.getInt("difficulty", 1)
        set(value) = prefs.edit().putInt("difficulty", value).apply()
}
