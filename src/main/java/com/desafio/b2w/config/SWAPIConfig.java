package com.desafio.b2w.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.b2w.external.service.SWAPIPlanetService;

@Configuration
public class SWAPIConfig {

	/**
	 * Base URL, used for all resources
	 */
	private final static String SWAPI_BASE_URL 		= "https://swapi.co/api/";
	
	/**
	 * All resources support a search parameter that filters the set of resources returned. 
	 */
	private final static String PLANET_SEARCH_FOR_NAME = "?search=";
	
	/**
	 * Resource for Planets
	 */
	private final static String PLANET_RESOURCE 		= "/planets/";
	
	@Bean
	public SWAPIPlanetService swapiService() {
		return new SWAPIPlanetService(SWAPI_BASE_URL, PLANET_RESOURCE, PLANET_SEARCH_FOR_NAME);
	}
}
