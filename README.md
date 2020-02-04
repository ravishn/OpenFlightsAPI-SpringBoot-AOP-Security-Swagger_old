# OpenFlightsAPI
SpringBoot application integrated with OpenFlights API and AOP implementation for REST API security
# OpenFlights API Specification
https://openflights.org/data.html

# Requirement
1. Use the Airline, Airport and Routes data from OpenFlights spec
2. Download and read the .dat files
3. Define a schema and store the data into an in-memory database
4. Provide API' for
    a. Flights flying into a specified airport
    b. Flights flyout out of a specified airport
    c. Flights between 2 specified airports
5. Authenticate and authorise endpoints using Basic Authorisation(username/password)
6. Document API using Swagger

# OpenFlightsAPI-SpringBoot
SpringBoot application integrated with OpenFlights data to get flights information from airports, airlines and routes

# Steps to run the API
Clone the OpenFlightsAPI-SpringBoot and open the project "com.foxtel.spring.demo"
1. Run Application.java - main Spring boot application
2. data.sql should accept relative path of the .dat files, if the application fails to launch, try providing the absolute path of the .dat files. Some versions of H2 database have this bug
3. Once the application is running, make a note of the port(should be 8080 by default)
4. Run the endpoint /flights/in/{airportId} with username and password in the AUTH headers if using Postman, enter credentials in the popup if using browser for flights flying in to the airport
Ex. http://localhost:8080/flights/in/2290 should return the JSON response
```
[
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "1",
        "airlineName": "Private flight",
        "destinataionAirportId": "2290",
        "sourceAirportId": null,
        "active": null,
        "stops": null
    },
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "3",
        "airlineName": "1Time Airline",
        "destinataionAirportId": "2290",
        "sourceAirportId": null,
        "active": null,
        "stops": null
    },
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "10",
        "airlineName": "40-Mile Air",
        "destinataionAirportId": "2290",
        "sourceAirportId": null,
        "active": null,
        "stops": null
    },
    ...
]
```

5. Run the endpoint /flights/out/{airportId} with username and password in the AUTH headers if using Postman, enter credentials in the popup if using browser for flights flying out from the airport
Ex. http://localhost:8080/flights/out/2290 should return the JSON response
```
[
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "1",
        "airlineName": "Private flight",
        "destinataionAirportId": null,
        "sourceAirportId": "2290",
        "active": null,
        "stops": null
    },
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "3",
        "airlineName": "1Time Airline",
        "destinataionAirportId": null,
        "sourceAirportId": "2290",
        "active": null,
        "stops": null
    },
    {
        "airportName": "Memanbetsu Airport",
        "airlineId": "10",
        "airlineName": "40-Mile Air",
        "destinataionAirportId": null,
        "sourceAirportId": "2290",
        "active": null,
        "stops": null
    },
    ...
]
```

6. Run the endpoint /flights/route/{sourceAirportId}/{destinationAirportId} with username and password in the AUTH headers if using Postman, enter credentials in the popup if using browser for flights flying between two airports
Ex. http://localhost:8080/flights/route/3393/3361 should return the JSON response
```
[
    {
        "airportName": null,
        "airlineId": null,
        "airlineName": "Private flight",
        "destinataionAirportId": "3361",
        "sourceAirportId": "3393",
        "active": "Y",
        "stops": "0"
    },
    {
        "airportName": null,
        "airlineId": null,
        "airlineName": "1Time Airline",
        "destinataionAirportId": "3361",
        "sourceAirportId": "3393",
        "active": "Y",
        "stops": "0"
    },
    {
        "airportName": null,
        "airlineId": null,
        "airlineName": "40-Mile Air",
        "destinataionAirportId": "3361",
        "sourceAirportId": "3393",
        "active": "Y",
        "stops": "0"
    },
    ...
]
```
# Steps to read Swagger API documentation
1. Run the endpoint http://localhost:8080/flights/api/docs. This will redirect to default Swagger documentation API http://localhost:8080/v2/api-docs which returns a JSON response.
2. Copy the JSON response and paste it on Swagger Editor webpage https://editor.swagger.io/

-OR-

1. Enable Swagger UI in the build.gradle - compile 'io.springfox:springfox-swagger-ui:2.9.2'
This exposes the Swagger API documentation on to the Swagger UI endpoint
2. Run the endpoint on the browser. This returns a HTML page where you can run the API's and test them out.
http://localhost:8080/flights/api/docs/ui. This will redirect to default Swagger documentation UI page http://localhost:8080/swagger-ui.html#/
