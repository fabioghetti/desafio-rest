package com.desafio.b2w.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.external.cache.PlanetaCache;

@Component
public class ScheduledTasks {

	private static final long TIMER_MINUTES = 15;
	
	@Autowired
	private PlanetaCache cache;
	
	@Scheduled(fixedRate = TIMER_MINUTES * 60000)
	public void carregaPlanetasAparicoesEmFilmes() throws ErroConversaoDadosExternosException {
		System.out.println("executou timer");
		cache.carregarDadosCache();
	}
}
