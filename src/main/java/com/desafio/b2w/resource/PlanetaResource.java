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

import com.desafio.b2w.dto.PlanetaTO;
import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.exception.PlanetaJaCadastradoException;
import com.desafio.b2w.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaService service;
	
	@GetMapping()
	public ResponseEntity<List<PlanetaTO>> buscarPlanetas(@RequestParam(value = "nome", required = false) String nome) throws ErroConversaoDadosExternosException {
		List<PlanetaTO> planetas = service.buscarPlanetas(nome);
		return planetas != null ? ResponseEntity.ok(planetas) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<PlanetaTO> criar(@Valid @RequestBody PlanetaTO planetaTO) throws ErroConversaoDadosExternosException, PlanetaJaCadastradoException {
		PlanetaTO planetaSalvo = service.criar(planetaTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanetaTO> buscarPeloId(@PathVariable String id) throws ErroConversaoDadosExternosException { 
		PlanetaTO planetaRecuperado = service.buscarPeloId(id);
		return planetaRecuperado != null ? ResponseEntity.ok(planetaRecuperado) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.remover(id);
	}
	
}