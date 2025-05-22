# Weather App

A simple Android weather application built with Kotlin.  
This app fetches and displays the current weather for a user-specified city using the [WeatherAPI.com](https://www.weatherapi.com/) service.

## Features

- Search for any city and get current weather information
- Displays temperature, weather condition, humidity, wind speed, and a weather icon
- Modern Android UI with EditText, Button, ImageView, and TextView
- Built using OkHttp for networking and Glide for image loading

## Screenshots

*(Add your screenshots here)*

## Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Android device or emulator running API 21 (Lollipop) or higher  
  > **Note:** Weather API may not work on very old Android versions (e.g., 5.1.1) due to outdated security certificates. For best results, use Android 7.0+.

### Setup

1. Clone this repository:
    ```sh
    git clone https://github.com/MohsinMuzamil/Weather-.git
    ```
2. Open in Android Studio.
3. Add your [WeatherAPI.com](https://www.weatherapi.com/) API key in `MainActivity.kt`:
    ```kotlin
    private val apiKey = "YOUR_API_KEY"
    ```
4. Build and run the app on your device or emulator.

## Dependencies

- [OkHttp](https://square.github.io/okhttp/) - HTTP & HTTP/2 client
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library

## Internet Permission

This app requires internet access.  
Ensure the following permission is present in your `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Troubleshooting

- If you see a "connection is not private" or SSL error, your Android version may not trust modern HTTPS certificates.  
  Please use a device or emulator with Android 7.0 (API 24) or newer.

## License

This project is for educational purposes.

---

**Author:** Mohsin Muzammil  
