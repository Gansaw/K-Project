package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.service.AuthService;

import lombok.Getter;
import lombok.Setter;

@RestController
public class BoardController {
	
	@Getter
	@Setter
    @Autowired
    private AuthService authService;
	
	@GetMapping("/home")
	public String home() {
		return "home.html";
	}
	
	@GetMapping("/siteInfo")
	public String siteInfo() {
		return "siteInfo";
	}
	
	@GetMapping("/mapApi")
	public String mapApi() {
		return "mapApi";
	}
	
	@GetMapping("/airportInfo")
	public String airportInfo() {
		return "airportInfo";
	}
	
	@GetMapping("/gallery")
	public String gallery() {
		return "gallery";
	}	

}
