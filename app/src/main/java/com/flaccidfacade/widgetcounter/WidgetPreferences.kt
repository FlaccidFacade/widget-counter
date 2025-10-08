package com.flaccidfacade.widgetcounter

import android.content.Context
import android.content.SharedPreferences

class WidgetPreferences private constructor(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    fun saveTitle(appWidgetId: Int, title: String) {
        prefs.edit().putString(getTitleKey(appWidgetId), title).apply()
    }

    fun getTitle(appWidgetId: Int): String {
        return prefs.getString(getTitleKey(appWidgetId), DEFAULT_TITLE) ?: DEFAULT_TITLE
    }

    fun saveCounter(appWidgetId: Int, count: Int) {
        prefs.edit().putInt(getCounterKey(appWidgetId), count).apply()
    }

    fun getCounter(appWidgetId: Int): Int {
        return prefs.getInt(getCounterKey(appWidgetId), 0)
    }

    fun deleteWidget(appWidgetId: Int) {
        prefs.edit()
            .remove(getTitleKey(appWidgetId))
            .remove(getCounterKey(appWidgetId))
            .apply()
    }

    private fun getTitleKey(appWidgetId: Int): String {
        return "${KEY_TITLE_PREFIX}_$appWidgetId"
    }

    private fun getCounterKey(appWidgetId: Int): String {
        return "${KEY_COUNTER_PREFIX}_$appWidgetId"
    }

    companion object {
        private const val PREFS_NAME = "com.flaccidfacade.widgetcounter.prefs"
        private const val KEY_TITLE_PREFIX = "title"
        private const val KEY_COUNTER_PREFIX = "counter"
        private const val DEFAULT_TITLE = "Counter"

        @Volatile
        private var instance: WidgetPreferences? = null

        fun getInstance(context: Context): WidgetPreferences {
            return instance ?: synchronized(this) {
                instance ?: WidgetPreferences(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }
}
