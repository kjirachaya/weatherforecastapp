# Weather Forecast App

### Project Description
This user-friendly weather forecast app, developed with Kotlin and Jetpack Compose, enables users to search for city weather by fetching data from the OpenWeatherMap API, displaying key information such as temperature and weather conditions. Utilizing the MVVM architecture and Hilt for dependency injection, the app features implement error handling to address invalid inputs and network failures, providing clear feedback to users during loading and error states.

### Features
- Search Weather by City: Users can input a city name and fetch weather data from api.openweathermap.org
- Display Weather Information: Shows temperature, weather description, and other relevant data.
- Error Handling: Displays error messages for invalid city names or network issues.
- Loading State: Shows a loading spinner while fetching data.

### Project Structure
![Project Structure](https://github.com/user-attachments/assets/d114e0a7-5dd5-49e3-b0a8-1ba4cfe79fdd)

## Getting Started
### Prerequisites
1. Android Studio
2. API Key: You’ll need an API key from OpenWeatherMap. Create a free account and get your API key.

### Setup Instructions
1. Clone the repository:
```
git clone https://github.com/kjirachaya/weatherforecastapp.git
```
2. Open the project in Android Studio.
3. Place your `OpenWeatherMap API Key` in a `key.xml` file in the res/values directory:
```xml
<resources>
    <string name="openweather_api_key">your_api_key_here</string>
</resources>
```
4. Build and run the project on an Android Emulator or physical device.

### How to Run the App
1. Select a device to run on (e.g., an Android Emulator with Pixel 7a)
2. Press the green “Run” button in Android Studio or use the shortcut Shift+F10.
3. Enter a city name in the input field and click “Forecast” to view the forecast.

### How to Use the App
<img src="https://github.com/user-attachments/assets/d8089ac0-626f-45a7-9d5d-1416914aab85" width="300"/>

1. Launch the app.
2. Enter the name of a city in the Enter city bar (e.g., London, New York, Tokyo).
3. Tap on “Forecast” to fetch the current weather data.
4. The app will display:
- Current temperature
- Weather description (clear, cloudy, etc.)
- Humidity and wind speed
- Sunrise and sunset times
- and more 🍀
5. If there is an issue with the network, an error message will be shown.
  
### Considerations
1. API Rate Limits: The free tier of the OpenWeatherMap API has rate limits. Be mindful of making too many requests within a short period, as you may be blocked from accessing the API.
2. Error Handling: The app provides error messages for invalid city names and network-related issues. Ensure you have a stable network connection when testing.
3. UI Testing: Unit and UI tests are not included in this version but can be added in the future to ensure reliability.

## Future Improvements
- Offline Support: Caching the last fetched data for offline use.
- UI Enhancements: Adding animations or weather icons for a more dynamic user experience.
- Multiple Cities: Allowing users to save and view the weather for multiple cities.
- City Suggestions: Implementing an API for autocomplete suggestions while typing city names.
- History Search: Keep a log of previously searched cities, allowing users to quickly access their weather data again.
- Bookmark City: Users can bookmark their favorite cities to access the weather information easily.
- 7-Day Forecast: Add a feature to display weather forecasts for the next 7 days.
