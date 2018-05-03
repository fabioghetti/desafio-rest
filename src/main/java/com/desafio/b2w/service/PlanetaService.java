package com.desafio.b2w.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.b2w.model.Planeta;
import com.desafio.b2w.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	public List<Planeta> buscarPlanetas(String nome) {
		if (nome == null || "".equals(nome)) {
			return planetaRepository.findAll();
		} else {
			return planetaRepository.findByNomeIgnoreCase(nome);
		}
	}

	public Planeta criar(Planeta planeta) {
		return planetaRepository.save(planeta);
	}

	public Planeta buscarPeloId(String id) {
		return planetaRepository.findById(id);
	}


	public void remover(String id) {
		planetaRepository.deleteById(id);
	}

	
}