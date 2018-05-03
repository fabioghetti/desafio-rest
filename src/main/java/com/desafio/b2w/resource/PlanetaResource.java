package com.desafio.b2w.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.b2w.model.Planeta;
import com.desafio.b2w.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaService service;
	
	@GetMapping()
	public ResponseEntity<List<Planeta>> buscarPlanetas(@RequestParam(value = "nome", required = false) String nome) {
		List<Planeta> planetas = service.buscarPlanetas(nome);
		return planetas != null ? ResponseEntity.ok(planetas) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Planeta> criar(@Valid @RequestBody Planeta planeta) {
		Planeta planetaSalvo = service.criar(planeta);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Planeta> buscarPeloId(@PathVariable String id) { 
		Planeta planetaRecuperado = service.buscarPeloId(id);
		return planetaRecuperado != null ? ResponseEntity.ok(planetaRecuperado) : ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.remover(id);
	}
	
	
}