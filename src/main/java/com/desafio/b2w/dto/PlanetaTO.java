package com.desafio.b2w.dto;

import org.hibernate.validator.constraints.NotBlank;

public class PlanetaTO {

	private String id;
	
	@NotBlank()
	private String nome;
	
	@NotBlank()
	private String clima;
	
	@NotBlank()
	private String terreno;
	
	private Integer totalAparicoes;

	public PlanetaTO() {
		
	}
	
	public PlanetaTO (final String id, final String nome, final String clima, final String terreno, final Integer totalAparicoes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.totalAparicoes = totalAparicoes;
	}
	
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

	public Integer getTotalAparicoes() {
		return totalAparicoes;
	}

	public void setTotalAparicoes(Integer totalAparicoes) {
		this.totalAparicoes = totalAparicoes;
	}
}