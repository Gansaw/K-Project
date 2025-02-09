package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.domain.Airport;
import com.airport.persistence.AirportRepo;

@RestController
public class AirportController {
	
	@Autowired
	private AirportRepo airportRepo;
	
	
	@RequestMapping("/airports")
	public Iterable<Airport> getAirports(){
		return airportRepo.findAll();
	}

}
