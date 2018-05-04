package com.desafio.b2w.external.cache;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.service.PlanetaService;

@Scope(value = "singleton")
@Component
public class PlanetaCache {
	
	@Autowired
	private PlanetaService service;
	
	private final boolean ativo = true;
	
	private HashMap<String, Integer> aparicoesFilmes = null;
	
	public Integer getValue(final String value) {
		return aparicoesFilmes.get(value);
	}
	
	public void put(final String key, final Integer value) {
		aparicoesFilmes.put(key, value);
	}
	
	public boolean contains(final String value) {
		return aparicoesFilmes.containsKey(value);
	}
	
	public boolean isCacheAtivo() {
		return ativo;
	}
	
	public void carregarDadosCache() {
		try {
			if (this.isCacheAtivo()) {
				aparicoesFilmes = new HashMap<String, Integer>();
				service.buscarPlanetas();
			}
		} catch (ErroConversaoDadosExternosException e) {
			e.printStackTrace();
		}
	}
}