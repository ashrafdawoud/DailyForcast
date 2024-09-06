This task required building a Daily Forecast Application using clean architecture principles and focusing on the following key areas:

    Fetching data from the OpenWeatherMap API.
    Caching data locally to handle offline scenarios.
    Handling errors gracefully with proper retry mechanisms.
    Displaying weather data for selected cities with relevant UI indicators.
    
Implementation Steps
1. Setting up the Project:

    Architecture: I followed the MVI architecture, which provides separation of concerns by clearly distinguishing between the UI, business logic, and data layers.
    Dependency Injection: Used Dagger Hilt for managing dependencies throughout the app. This allowed for clean and scalable object creation and lifecycle management.
    Retrofit: Implemented Retrofit to handle all network requests to the OpenWeatherMap API, including retrieving forecast data based on city latitude and longitude.
    Room Database: Used Room for local data storage and caching, ensuring that the user can still access weather data even if the network is unavailable.
   
3. Dropdown Menu for City Selection:

    Populated the city list dynamically in the dropdown menu with cities and their respective latitude and longitude.
    Handled user interaction to select a city, and automatically triggered the weather data retrieval process.

3. API Integration:

    Integrated the OpenWeatherMap API to fetch weather data. This API requires the latitude, longitude, and appId (API key).
    Implemented error handling for API failures, including timeouts and network issues.
    Cached API responses locally in the Room database to ensure the user can view previously fetched data when offline.

4. Data Caching and Offline Support:

    Implemented Room database entities, DAOs, and repositories to store and retrieve weather data locally.
    Upon a successful API response, the data is cached locally. In case of API failure, the app checks for existing cached data and displays it with a warning that the data may not be up-to-date.

5. Error Handling:

    Managed different types of errors, such as network failures, API response failures, and no cached data, by showing appropriate error messages to the user.
    Provided a Retry option in case of network errors to allow the user to attempt fetching the data again.
