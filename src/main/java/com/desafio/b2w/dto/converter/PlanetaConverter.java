package com.desafio.b2w.dto.converter;

import com.desafio.b2w.dto.PlanetaTO;
import com.desafio.b2w.model.Planeta;

public class PlanetaConverter {

	public static PlanetaTO convertToTransferObject(final Planeta planeta, final Integer totalAparicoes) {
		return new PlanetaTO(planeta.getId(), planeta.getNome(), planeta.getClima(), planeta.getTerreno(), totalAparicoes);
	}
	
	public static Planeta converToObject(final PlanetaTO planetaTO) {
		return new Planeta(planetaTO.getId(), planetaTO.getNome(), planetaTO.getClima(), planetaTO.getTerreno());
	}
	
}