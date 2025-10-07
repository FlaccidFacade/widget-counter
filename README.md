# Widget Counter

An Android home screen widget for counting with a customizable title and plus/minus functionality.

## Features

- **Editable Title**: Configure a custom title for each widget instance
- **Counter Display**: Shows the current count with large, easy-to-read text
- **Plus/Minus Buttons**: Increment or decrement the counter with simple button taps
- **Multiple Instances**: Add multiple counter widgets to your home screen, each with its own title and count
- **Persistent Storage**: Counter values and titles are saved across device reboots
- **Material Design**: Modern, clean interface with Material Design components

## Technical Details

- **Built with**: Kotlin and Android SDK
- **Min SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 13 (API 33)
- **Architecture**: Uses SharedPreferences for data persistence
- **Jetpack Components**: Utilizes AndroidX libraries including AppCompat and Material Components

## Building the Project

### Prerequisites

- Android Studio (latest version recommended)
- JDK 17 or higher
- Android SDK with API 33

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/FlaccidFacade/widget-counter.git
   cd widget-counter
   ```

2. Open the project in Android Studio or build from command line:
   ```bash
   ./gradlew build
   ```

3. To build a debug APK:
   ```bash
   ./gradlew assembleDebug
   ```

4. The APK will be located at: `app/build/outputs/apk/debug/app-debug.apk`

## Installation

### From Source

1. Build the project as described above
2. Install the APK on your device:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

### Adding the Widget

1. Long-press on your home screen
2. Select "Widgets" from the menu
3. Find "Counter Widget" in the widget list
4. Drag it to your home screen
5. Configure the widget title in the setup dialog
6. Tap "Save"

## Usage

- **Configure**: When adding a new widget, enter a custom title or use the default "Counter"
- **Increment**: Tap the "+" button to increase the count
- **Decrement**: Tap the "-" button to decrease the count
- **Multiple Widgets**: Add as many widgets as you need, each maintains its own count

## CI/CD

The project includes GitHub Actions workflows for:

- **Continuous Integration**: Automatically builds and tests the app on every push/PR to main
- **Release**: Creates releases with APK artifacts when tags are pushed (format: `v*`)

### Creating a Release

To create a new release:

```bash
git tag v1.0.0
git push origin v1.0.0
```

This will trigger the release workflow and create a GitHub release with the APK attached.

## Project Structure

```
widget-counter/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/flaccidfacade/widgetcounter/
│   │       │   ├── CounterWidgetReceiver.kt       # Main widget provider
│   │       │   ├── WidgetPreferences.kt          # Data storage
│   │       │   └── CounterWidgetConfigActivity.kt # Configuration UI
│   │       ├── res/
│   │       │   ├── layout/                        # Widget and activity layouts
│   │       │   ├── values/                        # Strings, colors, themes
│   │       │   ├── xml/                           # Widget metadata
│   │       │   └── drawable/                      # Icons and drawables
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── .github/
│   └── workflows/
│       ├── android-build.yml    # CI workflow
│       └── android-release.yml  # Release workflow
└── build.gradle.kts

```

## License

This project is open source and available for use and modification.

## Contributing

Contributions are welcome! Feel free to submit issues and pull requests. 
