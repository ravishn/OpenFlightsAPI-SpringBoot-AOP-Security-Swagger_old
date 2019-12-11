package com.foxtel.spring.demo.controller;

import java.util.List;

import com.foxtel.spring.demo.model.Flight;
import com.foxtel.spring.demo.service.FlightService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for defining the API endpoints for fetching flights
 * 1. flying into an airport
 * 2. flying out from an airport
 * 3. between airports
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	FlightService flightService;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	/**
	 * Endpoint to fetch flights flying into an airport by airportId
	 * 
	 * @param airportId
	 * @return flights
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/in/airport/{airportId}", method = RequestMethod.GET, produces = "application/json")
	// @EnableAccessControl(authUserName = "admin", authPassword = "admin")
	public List<Flight> getAllFlightsIn(@PathVariable String airportId) throws InterruptedException {
		
		logger.info("API called for all flights to " +airportId);

		List<Flight> flightsIn = flightService.getAllFlightsIn(airportId);

		return flightsIn;
	}

	/**
	 * Endpoint to fetch flights flying out from an airport by airportId
	 * 
	 * @param airportId
	 * @return flights
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/out/airport/{airportId}", method = RequestMethod.GET, produces = "application/json")
	// @EnableAccessControl(authUserName = "admin", authPassword = "admin")
	public List<Flight> getAllFlightsOut(@PathVariable String airportId) throws InterruptedException {

		logger.info("API called for all flights from " +airportId);
				
		List<Flight> flightsOut = flightService.getAllFlightsOut(airportId);

		return flightsOut;
	}

	/**
	 * Endpoint to fetch flights flying between airports by source and destination
	 * airportIds
	 * 
	 * @param sourceAirportId
	 * @param destinationAirportId
	 * @return flights
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/route/source/{sourceAirportId}/dest/{destinationAirportId}", method = RequestMethod.GET, produces = "application/json")
	// @EnableAccessControl(authUserName = "admin", authPassword = "admin")
	public List<Flight> getFlightsFromAndToAirports(@PathVariable String sourceAirportId,
			@PathVariable String destinationAirportId) throws InterruptedException {
		
				logger.info("API called for all flights from " +sourceAirportId + " to airport " +destinationAirportId);
		
		List<Flight> flightsBetweenAirports = flightService.getAllFlightsBetweenAirportsByAirportId(sourceAirportId,
				destinationAirportId);

		return flightsBetweenAirports;
	}
}
