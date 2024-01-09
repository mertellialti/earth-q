# EarthQ Backend

## Overview
EarthQ is a Spring Boot-based backend application that interfaces with the USGS Earthquake API to provide real-time earthquake data. It filters and processes earthquake information based on specific regions and timeframes. This data is then utilized by a frontend application to display earthquakes happening in regions like Turkey, Ankara, Europe, etc., with filters for day, month, and hour.

## Features
- **Real-Time Earthquake Data**: Fetches data from the USGS Earthquake API.
- **Regional Filtering**: Allows filtering of earthquake data by specific regions.
- **Time-Based Filtering**: Users can filter earthquake occurrences by day, month, or hour.
- **Future Expansion (v2)**: Plans to store user locations periodically for sharing with emergency services like police, AFAD, etc., in case of earthquakes.

## Prerequisites
Ensure you have the following installed:
- Java JDK 11 or later.
- Maven for project dependency management.
- Your preferred IDE for Java development (e.g., IntelliJ IDEA, Eclipse).

## Installation

1. **Clone the Repository**
   ```bash
   git clone [your-repository-link]
   cd EarthQ
   ```
2. **Install Dependencies**
   
Navigate to the project root directory and run:
```
mvn clean install
```
## Running the Application

1. **Start the Server**

In the project root directory, execute:
```
mvn spring-boot:run
```

This will start the Spring Boot server on the default port (usually 8080).

2. **API Endpoints**

**GET | earthquake/earthquake-list: Unified endpoint to fetch earthquake data.**
* Query Parameters:
  * startDate & endDate: Filters earthquakes by date range. If provided, includes dates in filtering.
  * minMagnitude & maxMagnitude: Filters earthquakes by magnitude range. If provided, includes magnitude in filtering.
  * If neither date nor magnitude parameters are provided, the endpoint returns all earthquake data.

* /locations: (v2) Manages user location data.
* More endpoints can be documented as they are developed.

## Contributing

Contributions are welcome. Please fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License.

Contact

For queries or feedback, please contact [Your Email or GitHub Profile].
