package com.desafio.b2w.external.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author fghetti-dev
 * Wrapper responsavel por envelopar o retorno do SWAPIService
 * So estao sendo mapeado os campos que sao utilizados no momento
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaSWAPIWrapper extends SWAPIWrapper {

	@JsonProperty("results")
	private List<PlanetaSWAPI> planetas;

	public List<PlanetaSWAPI> getPlanetas() {
		return planetas;
	}

	public void setPlanetas(List<PlanetaSWAPI> planetas) {
		this.planetas = planetas;
	}
	
}