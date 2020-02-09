package com.foxtel.spring.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxtel.spring.demo.model.Flight;
import com.foxtel.spring.demo.repository.FlightJdbcRepository;

/**
 * Service class to fetch flight details from the request
 */
@Component
public class FlightService {

    //Database query class connected via JDBC
    @Autowired
    FlightJdbcRepository flightJdbcRepository;

    // List of flights to be returned to the caller
    List<Flight> flightsIn;

    // List of flights to be returned to the caller
    List<Flight> flightsOut;

    // List of flights to be returned to the caller
    List<Flight> flightsBetween;

    //Airport ID to be fetched from the request method
    String airportId;

    //Source AirportID to be fetched from the request method
    String sourceAirportId;

    //Destination AirportID to be fetched from the request method
    String destinationAirportId;
    
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    /**
     * method to fetch all the flights coming in to an airport
     * @param airportId
     * @return flightsIn
     */
    public List<Flight> getAllFlightsIn(String airportId) {

    	logger.debug("Service request for flights to " +airportId);
    	
        this.airportId = airportId;

        flightsIn = flightJdbcRepository.findFlightsInByDestinationAirportId(airportId);

        return flightsIn;
    }

    /**
     * method to fetch all the flights going out from an airport
     * @param airportId
     * @return flightsOut
     */
    public List<Flight> getAllFlightsOut(String airportId) {
    	
    	logger.debug("Service request for flights from " +airportId);

        this.airportId = airportId;

        flightsOut = flightJdbcRepository.findFlightsOutBySurceAirportId(airportId);

        return flightsOut;
    }

    /**
     * method to fetch all flights between two airports
     * @param sourceAirportId
     * @param destinationAirportId
     * @return flightsBetween
     */
    public List<Flight> getAllFlightsBetweenAirportsByAirportId(String sourceAirportId, String destinationAirportId) {

    	logger.debug("Service request for flights between " +sourceAirportId + " and " +destinationAirportId);
    	
        this.sourceAirportId = sourceAirportId;
        this.destinationAirportId = destinationAirportId;

        flightsBetween = flightJdbcRepository.findFlightsBetweenAirports(sourceAirportId, destinationAirportId);

        return flightsBetween;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
