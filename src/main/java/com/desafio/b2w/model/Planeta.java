package com.desafio.b2w.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class Planeta {

	@Id
	private String id;
	
	@NotBlank(message = "test.dois.testa")
	private String nome;
	
	@NotBlank
	private String clima;
	
	@NotBlank
	private String terreno;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

}