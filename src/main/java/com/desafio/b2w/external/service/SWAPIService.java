package com.desafio.b2w.external.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.external.model.SWAPIWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SWAPIService {

	@Autowired
	protected RestTemplate restTemplate;
	
	/**
	 * The Base URL is the root URL for all of the API.
	 */
	protected String baseURL;
	
	/**
	 * can be films, people, planets, species, starships, vehicles
	 */
	protected String resource;
	
	/**
	 * All resources support a search parameter that filters the set of resources returned.
	 */
	protected String searching;
	
	
	public SWAPIService(final String baseURL, final String resource, final String searching) {
		this.baseURL = baseURL;
		this.resource = resource;
		this.searching = searching;
	}
	
	private HttpEntity<String> createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("user-agent", "desafio-b2w");
		return new HttpEntity<String>("parameters", headers);
	}
	
	private String createResourceSearch(final String param) {
		return this.baseURL+this.resource+this.searching+param;
	}
	
	protected SWAPIWrapper searchInResource(String param, Class<? extends SWAPIWrapper> wrapperClass) throws ErroConversaoDadosExternosException {
		ResponseEntity<String> response = restTemplate.exchange(
				this.createResourceSearch(param), 
				HttpMethod.GET, this.createHeaders(), String.class);
		return getFromJson(response.getBody(), wrapperClass);
		
	}
	
	private SWAPIWrapper getFromJson(String json, Class<? extends SWAPIWrapper> wrapperClass) throws ErroConversaoDadosExternosException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, wrapperClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErroConversaoDadosExternosException();
		}
	}
}