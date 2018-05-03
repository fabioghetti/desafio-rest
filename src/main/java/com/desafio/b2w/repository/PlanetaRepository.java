package com.desafio.b2w.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.desafio.b2w.model.Planeta;

public interface PlanetaRepository extends Repository<Planeta, String> {

	
	public Planeta save(Planeta planeta);
	public List<Planeta> findAll();
	public void deleteById(String id);
	public Planeta findById(String id);
	public List<Planeta> findByNomeIgnoreCase(String nome);
	

	

	

	
}