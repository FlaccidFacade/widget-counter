package com.flaccidfacade.widgetcounter

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class CounterWidgetConfigActivity : AppCompatActivity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private lateinit var titleInput: TextInputEditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_config)

        // Set the result to CANCELED initially
        setResult(RESULT_CANCELED)

        // Get the app widget ID from the intent
        appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        // If this activity was started with an invalid ID, finish immediately
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        titleInput = findViewById(R.id.title_input)
        saveButton = findViewById(R.id.save_button)

        // Set default title
        titleInput.setText(getString(R.string.default_title))

        saveButton.setOnClickListener {
            saveWidgetConfiguration()
        }
    }

    private fun saveWidgetConfiguration() {
        val title = titleInput.text?.toString()?.trim() ?: getString(R.string.default_title)
        
        // Save the configuration
        val prefs = WidgetPreferences.getInstance(this)
        prefs.saveTitle(appWidgetId, title)
        prefs.saveCounter(appWidgetId, 0) // Initialize counter to 0

        // Update the widget
        val appWidgetManager = AppWidgetManager.getInstance(this)
        CounterWidgetReceiver.updateAppWidget(this, appWidgetManager, appWidgetId)

        // Return success
        val resultValue = Intent().apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        setResult(RESULT_OK, resultValue)
        finish()
    }
}
