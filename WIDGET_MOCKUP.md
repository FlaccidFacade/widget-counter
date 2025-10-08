# Widget Counter - Visual Mockup

## Widget Appearance

### Home Screen Widget

```
┌────────────────────────────────────┐
│                                    │
│         🔢 Counter Widget          │
│                                    │
│  ┌──────────────────────────────┐  │
│  │                              │  │
│  │        My Counter            │  │  ← Custom title (editable)
│  │                              │  │
│  │           42                 │  │  ← Current count (large)
│  │                              │  │
│  │       ┌───┐    ┌───┐        │  │
│  │       │ - │    │ + │        │  │  ← Decrement/Increment
│  │       └───┘    └───┘        │  │
│  │                              │  │
│  └──────────────────────────────┘  │
│                                    │
└────────────────────────────────────┘
```

### Configuration Screen

```
┌────────────────────────────────────┐
│                                    │
│   Configure Widget                 │
│                                    │
│  ┌──────────────────────────────┐  │
│  │ Enter widget title           │  │
│  │ [_________________________]  │  │
│  └──────────────────────────────┘  │
│                                    │
│  ┌──────────────────────────────┐  │
│  │          SAVE                │  │
│  └──────────────────────────────┘  │
│                                    │
└────────────────────────────────────┘
```

## Widget States

### Initial State (Just Added)
```
┌─────────────────────┐
│      Counter        │  ← Default title
│         0           │  ← Starting value
│    [ - ]  [ + ]     │
└─────────────────────┘
```

### After Custom Configuration
```
┌─────────────────────┐
│   Workout Reps      │  ← User's custom title
│         0           │  ← Initial counter
│    [ - ]  [ + ]     │
└─────────────────────┘
```

### After Several Increments
```
┌─────────────────────┐
│   Workout Reps      │
│        25           │  ← Updated count
│    [ - ]  [ + ]     │
└─────────────────────┘
```

### Multiple Instances Example
```
┌────────────────────┐  ┌────────────────────┐
│   Workout Reps     │  │   Water Glasses    │
│       25           │  │        8           │
│   [ - ]  [ + ]     │  │   [ - ]  [ + ]     │
└────────────────────┘  └────────────────────┘

┌────────────────────┐  ┌────────────────────┐
│   Books Read       │  │   Days Streak      │
│       12           │  │        47          │
│   [ - ]  [ + ]     │  │   [ - ]  [ + ]     │
└────────────────────┘  └────────────────────┘
```

## Color Scheme

### Light Theme
- **Background**: White (#FFFFFF)
- **Border**: Light gray (#CCCCCC)
- **Text**: Black (#000000)
- **Buttons**: Purple (#6200EE)
- **Button Text**: White

### Design Details
- **Corner Radius**: 16dp (rounded corners)
- **Padding**: 16dp (internal spacing)
- **Button Size**: 60x60dp (square buttons)
- **Title Font**: 18sp, bold
- **Counter Font**: 32sp, bold
- **Button Font**: 24sp

## User Interaction Flow

### Adding a Widget

```
1. Home Screen
   ↓
2. Long Press → Widget Picker
   ↓
3. Select "Counter Widget"
   ↓
4. Drag to Home Screen
   ↓
5. Configuration Screen Appears
   ┌─────────────────────┐
   │ Configure Widget    │
   │ Title: [Counter___] │
   │     [SAVE]          │
   └─────────────────────┘
   ↓
6. Enter Custom Title (e.g., "Steps Today")
   ↓
7. Tap SAVE
   ↓
8. Widget Appears on Home Screen
   ┌─────────────────────┐
   │   Steps Today       │
   │        0            │
   │   [ - ]  [ + ]      │
   └─────────────────────┘
```

### Using the Widget

```
Counter at 0
┌─────────────────────┐
│   Steps Today       │
│        0            │
│   [ - ]  [ + ]←TAP  │
└─────────────────────┘
        ↓
Counter increases to 1
┌─────────────────────┐
│   Steps Today       │
│        1            │
│   [ - ]  [ + ]      │
└─────────────────────┘
        ↓
Tap + again
        ↓
┌─────────────────────┐
│   Steps Today       │
│        2            │
│   [ - ]  [ + ]      │
└─────────────────────┘
        ↓
Tap - to decrease
        ↓
┌─────────────────────┐
│   Steps Today       │
│        1            │
│   [ - ]←TAP [ + ]   │
└─────────────────────┘
```

## Size Specifications

### Minimum Widget Size
- **Width**: 180dp
- **Height**: 110dp
- **Grid Cells**: 2x2 (approximately)

### Recommended Size
- **Width**: 240dp
- **Height**: 120dp
- **Grid Cells**: 3x2

### Resizable
- The widget can be resized both horizontally and vertically
- Adapts to different home screen grid sizes
- Maintains readable text at all sizes

## Typography Hierarchy

```
┌─────────────────────┐
│   Large Title      │  ← 18sp, Bold, Black
│      1234          │  ← 32sp, Bold, Black (emphasis)
│   [ - ]  [ + ]     │  ← 24sp, White (buttons)
└─────────────────────┘
```

## Responsive Behavior

### Small Size (2x1)
```
┌────────────┐
│  Counter   │
│     5      │
│  [-] [+]   │
└────────────┘
```

### Medium Size (3x2) - Recommended
```
┌─────────────────────┐
│     Counter         │
│        5            │
│    [ - ]  [ + ]     │
└─────────────────────┘
```

### Large Size (4x2)
```
┌──────────────────────────┐
│       Counter            │
│          5               │
│     [ - ]    [ + ]       │
└──────────────────────────┘
```

## Button States

### Normal
```
┌───┐
│ + │  Background: Purple (#6200EE)
└───┘  Text: White
```

### Pressed (Visual Feedback)
```
┌───┐
│ + │  Background: Darker Purple
└───┘  Elevation/Shadow effect
```

## Real-World Use Cases

1. **Fitness Tracker**
   - "Push-ups Today" - Track daily exercises
   - "Steps Walked" - Count steps manually
   - "Water Glasses" - Track hydration

2. **Habit Tracking**
   - "Days Clean" - Sobriety counter
   - "Meditation Days" - Streak tracker
   - "Books Read" - Reading goal

3. **Work/Productivity**
   - "Tasks Done" - Daily task counter
   - "Pomodoros" - Time blocks completed
   - "Cups of Coffee" - Daily intake

4. **Gaming/Fun**
   - "High Score" - Personal records
   - "Wins Today" - Gaming statistics
   - "Lucky Number" - Random tracking

5. **Daily Life**
   - "Packages Delivered" - Delivery tracking
   - "Visitors" - Guest counter
   - "Days Until" - Countdown (manual)

## Accessibility

- **Large Touch Targets**: Buttons are 60x60dp for easy tapping
- **High Contrast**: Black text on white background
- **Clear Typography**: Bold fonts for readability
- **Simple Layout**: Easy to understand at a glance
- **No Time Pressure**: Users can interact at their own pace

## Widget Limitations

✅ **What It Does:**
- Displays a customizable title
- Shows a counter value
- Increments/decrements on button press
- Persists data across reboots
- Supports multiple instances

❌ **What It Doesn't Do:**
- No automatic updates
- No network connectivity
- No notifications
- No graphs or statistics
- No data export
- No widget title editing after creation

## Future Enhancement Ideas

1. **Long-press to reset** - Hold button to reset to 0
2. **Background colors** - Choose widget color theme
3. **Custom increment** - Set step size (1, 5, 10, etc.)
4. **History** - View past values
5. **Goals** - Set target values with progress
6. **Themes** - Dark mode support
