package com.desafio.b2w.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.b2w.dto.PlanetaTO;
import com.desafio.b2w.dto.converter.PlanetaConverter;
import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.exception.PlanetaJaCadastradoException;
import com.desafio.b2w.external.service.SWAPIPlanetService;
import com.desafio.b2w.model.Planeta;
import com.desafio.b2w.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private SWAPIPlanetService swapiService;
	
	public List<PlanetaTO> buscarPlanetas() throws ErroConversaoDadosExternosException {
		return buscarPlanetas(null);
	}
	
	public List<PlanetaTO> buscarPlanetas(String nome) throws ErroConversaoDadosExternosException {
		List<Planeta> planetasEncontrados = new ArrayList<Planeta>();
		if (nome == null || "".equals(nome)) {
			planetasEncontrados = planetaRepository.findAll();
		} else {
			planetasEncontrados = planetaRepository.findByNomeIgnoreCase(nome);
		}
		List<PlanetaTO> planetasTO = new ArrayList<PlanetaTO>();
		for (Planeta planeta : planetasEncontrados) {
			planetasTO.add(PlanetaConverter.convertToTransferObject(
					planeta, swapiService.getTotalAparicoesFilmes(planeta.getNome())));
		}
		return planetasTO;
	}

	public PlanetaTO criar(PlanetaTO planetaTO) throws ErroConversaoDadosExternosException, PlanetaJaCadastradoException {
		Planeta planeta = PlanetaConverter.converToObject(planetaTO);
		
		if (!this.isPlanetaJaCadastrado(planeta.getNome())) {
			return PlanetaConverter.convertToTransferObject(
					planetaRepository.save(planeta), 
					swapiService.getTotalAparicoesFilmes(planetaTO.getNome()));
		} else {
			throw new PlanetaJaCadastradoException();
		}
	}

	public PlanetaTO buscarPeloId(String id) throws ErroConversaoDadosExternosException {
		Planeta planeta = planetaRepository.findById(id);
		return planeta != null ? PlanetaConverter.convertToTransferObject(planeta, 
				swapiService.getTotalAparicoesFilmes(planeta.getNome())) : null;
	}

	public void remover(String id) {
		planetaRepository.deleteById(id);
	}
	
	private boolean isPlanetaJaCadastrado(final String nome) {
		List<Planeta> planetas = planetaRepository.findByNomeIgnoreCase(nome);
		if (planetas == null || planetas.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}