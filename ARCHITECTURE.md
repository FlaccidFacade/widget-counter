# Architecture Documentation

## Widget Counter Architecture

### Overview

The Widget Counter is an Android home screen widget built using the AppWidget framework with Kotlin. It allows users to create multiple counter widgets, each with a customizable title and independent counter value.

### Components

#### 1. CounterWidgetReceiver (AppWidgetProvider)

The main widget provider that handles:
- Widget creation and updates (`onUpdate`)
- User interactions via broadcast intents (`onReceive`)
- Widget deletion and cleanup (`onDeleted`)

**Key Methods:**
- `updateAppWidget()`: Updates the widget UI with current data
- `onReceive()`: Handles INCREMENT and DECREMENT actions
- `onDeleted()`: Cleans up stored preferences when widget is removed

#### 2. CounterWidgetConfigActivity (Activity)

Configuration activity shown when user adds a new widget:
- Allows user to set a custom title
- Initializes counter to 0
- Saves configuration and updates the widget

**Flow:**
1. User adds widget to home screen
2. Configuration activity launches
3. User enters title (or uses default)
4. Activity saves preferences and updates widget
5. Returns RESULT_OK to complete widget placement

#### 3. WidgetPreferences (Singleton)

Data persistence layer using SharedPreferences:
- Stores widget titles (key: `title_{widgetId}`)
- Stores counter values (key: `counter_{widgetId}`)
- Provides thread-safe singleton access

**Methods:**
- `saveTitle()`, `getTitle()`: Manage widget titles
- `saveCounter()`, `getCounter()`: Manage counter values
- `deleteWidget()`: Remove all data for a widget

### Data Flow

```
User Adds Widget
    ↓
CounterWidgetConfigActivity
    ↓
Enter Title → Save to WidgetPreferences
    ↓
updateAppWidget() → Display on Home Screen

User Taps + or -
    ↓
PendingIntent Broadcast
    ↓
CounterWidgetReceiver.onReceive()
    ↓
Update Counter in WidgetPreferences
    ↓
updateAppWidget() → Refresh Display
```

### Widget Layout Structure

```
┌─────────────────────────┐
│                         │
│      Widget Title       │  ← Editable during setup
│                         │
│          42             │  ← Counter value
│                         │
│      [ - ]  [ + ]       │  ← Decrement/Increment buttons
│                         │
└─────────────────────────┘
```

### PendingIntent Strategy

Each widget instance uses unique PendingIntent request codes:
- Increment button: `widgetId * 2`
- Decrement button: `widgetId * 2 + 1`

This ensures each widget's buttons work independently even with multiple instances.

### Storage Schema

SharedPreferences keys:
- `title_{widgetId}`: String - Widget title
- `counter_{widgetId}`: Int - Counter value

### Lifecycle

**Widget Creation:**
1. User long-presses home screen
2. Selects widget from widget picker
3. `CounterWidgetConfigActivity` launches
4. User configures title
5. Widget is placed with initial counter = 0

**Widget Interaction:**
1. User taps +/- button
2. PendingIntent triggers broadcast
3. `CounterWidgetReceiver.onReceive()` processes action
4. Counter updated in SharedPreferences
5. Widget UI refreshed via RemoteViews

**Widget Deletion:**
1. User removes widget from home screen
2. `CounterWidgetReceiver.onDeleted()` called
3. Widget data removed from SharedPreferences

### Material Design

The widget uses Material Design components:
- Material Button styling with custom tint
- Rounded corners (16dp radius)
- Material color palette (Purple/Teal)
- Consistent spacing and typography

### Dependencies

**Core Android:**
- `androidx.core:core-ktx` - Kotlin extensions
- `androidx.appcompat:appcompat` - Compatibility support

**UI:**
- `com.google.android.material:material` - Material Design components

**Jetpack:**
- `androidx.glance:glance-appwidget` - Modern widget framework (future)
- `androidx.datastore:datastore-preferences` - Alternative storage (not currently used)

### Future Enhancements

1. **Jetpack Glance**: Migrate to Glance for Compose-based widgets
2. **DataStore**: Replace SharedPreferences with DataStore
3. **Reset Button**: Add option to reset counter to 0
4. **Custom Colors**: Allow users to customize widget appearance
5. **Step Size**: Configurable increment/decrement amount
6. **Widget Resizing**: Better support for different widget sizes
7. **Themes**: Light/dark theme support
8. **Statistics**: Track total increments/decrements

### Testing Strategy

The widget should be tested for:
1. **Configuration**: Title input and saving
2. **Multiple Instances**: Independent operation of multiple widgets
3. **Persistence**: Data survives app restart and device reboot
4. **Edge Cases**: Negative numbers, large numbers, empty titles
5. **Memory**: No memory leaks from PendingIntents
6. **Cleanup**: Proper data deletion when widget removed

### CI/CD Pipeline

**Build Workflow** (`android-build.yml`):
- Triggers on push/PR to main
- Sets up JDK 17
- Builds with Gradle
- Uploads debug APK as artifact

**Release Workflow** (`android-release.yml`):
- Triggers on version tags (v*)
- Builds release APK
- Creates GitHub release
- Attaches APK to release

### Security Considerations

1. **PendingIntent Immutability**: Uses `FLAG_IMMUTABLE` for security
2. **Data Isolation**: Each widget's data is isolated by widgetId
3. **No Network**: Widget operates entirely offline
4. **No Permissions**: No dangerous permissions required

### Performance

- **Fast Updates**: Direct SharedPreferences access
- **Minimal CPU**: Only updates on user interaction
- **No Background Work**: No periodic updates or services
- **Small Memory Footprint**: Simple data structures

### Compatibility

- **Min SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 13 (API 33)
- **Supports**: All device sizes and orientations
- **Works on**: Phones, tablets, and foldables
