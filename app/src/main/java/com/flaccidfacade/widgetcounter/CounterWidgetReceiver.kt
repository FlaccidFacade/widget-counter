package com.flaccidfacade.widgetcounter

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class CounterWidgetReceiver : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        
        if (intent.action == ACTION_INCREMENT || intent.action == ACTION_DECREMENT) {
            val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 
                AppWidgetManager.INVALID_APPWIDGET_ID)
            
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                val prefs = WidgetPreferences.getInstance(context)
                val currentCount = prefs.getCounter(appWidgetId)
                
                val newCount = when (intent.action) {
                    ACTION_INCREMENT -> currentCount + 1
                    ACTION_DECREMENT -> currentCount - 1
                    else -> currentCount
                }
                
                prefs.saveCounter(appWidgetId, newCount)
                
                val appWidgetManager = AppWidgetManager.getInstance(context)
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val prefs = WidgetPreferences.getInstance(context)
        for (appWidgetId in appWidgetIds) {
            prefs.deleteWidget(appWidgetId)
        }
    }

    companion object {
        const val ACTION_INCREMENT = "com.flaccidfacade.widgetcounter.INCREMENT"
        const val ACTION_DECREMENT = "com.flaccidfacade.widgetcounter.DECREMENT"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val prefs = WidgetPreferences.getInstance(context)
            val title = prefs.getTitle(appWidgetId)
            val counter = prefs.getCounter(appWidgetId)

            val views = RemoteViews(context.packageName, R.layout.counter_widget_layout)
            views.setTextViewText(R.id.widget_title, title)
            views.setTextViewText(R.id.widget_counter, counter.toString())

            // Set up increment button
            val incrementIntent = Intent(context, CounterWidgetReceiver::class.java).apply {
                action = ACTION_INCREMENT
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            val incrementPendingIntent = PendingIntent.getBroadcast(
                context,
                appWidgetId * 2,
                incrementIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.button_increment, incrementPendingIntent)

            // Set up decrement button
            val decrementIntent = Intent(context, CounterWidgetReceiver::class.java).apply {
                action = ACTION_DECREMENT
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            val decrementPendingIntent = PendingIntent.getBroadcast(
                context,
                appWidgetId * 2 + 1,
                decrementIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.button_decrement, decrementPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
