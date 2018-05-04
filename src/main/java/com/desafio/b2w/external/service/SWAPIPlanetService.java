package com.desafio.b2w.external.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.external.cache.PlanetaCache;
import com.desafio.b2w.external.model.PlanetaSWAPI;
import com.desafio.b2w.external.model.PlanetaSWAPIWrapper;

public class SWAPIPlanetService extends SWAPIService {

	@Autowired
	private PlanetaCache planetaCache;
	
	public SWAPIPlanetService(final String baseURL, final String planetResource, final String planetSearchForName) {
		super(baseURL, planetResource, planetSearchForName);
	}
	
	public PlanetaSWAPIWrapper buscaPorPlanetas(final String nomeDoPlaneta) throws ErroConversaoDadosExternosException {
		return (PlanetaSWAPIWrapper)this.searchInResource(nomeDoPlaneta, PlanetaSWAPIWrapper.class);
	}
	
	public Integer getTotalAparicoesFilmes(final String nomeDoPlaneta) throws ErroConversaoDadosExternosException {
		if (planetaCache.isCacheAtivo() && planetaCache.contains(nomeDoPlaneta)) {
			return planetaCache.getValue(nomeDoPlaneta);
		} else {
			Integer totalAparicoes = 0;
			PlanetaSWAPIWrapper wrapper = this.buscaPorPlanetas(nomeDoPlaneta);
			if (wrapper != null && !wrapper.getPlanetas().isEmpty()) {
				PlanetaSWAPI planetaSWAPI = wrapper.getPlanetas().get(0);
				if (planetaSWAPI != null && planetaSWAPI.getFilms().length > 0) {
					totalAparicoes = planetaSWAPI.getFilms().length;
				}
			}
			if (planetaCache.isCacheAtivo()) {
				planetaCache.put(nomeDoPlaneta, totalAparicoes);
			}
			return totalAparicoes;
		}
		
	}
	
	
}