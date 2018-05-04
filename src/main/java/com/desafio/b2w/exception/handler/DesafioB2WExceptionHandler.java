package com.desafio.b2w.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.desafio.b2w.exception.ErroConversaoDadosExternosException;
import com.desafio.b2w.exception.PlanetaJaCadastradoException;

@ControllerAdvice
public class DesafioB2WExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({ PlanetaJaCadastradoException.class } )
	public ResponseEntity<Object> handlePlanetaJaCadastradoException(PlanetaJaCadastradoException ex, WebRequest request) {		
		List<Erro> erros = Arrays.asList(new Erro(
				messageSource.getMessage("erro.planeta.ja.cadastrado", null, LocaleContextHolder.getLocale())));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler({ ErroConversaoDadosExternosException.class } )
	public ResponseEntity<Object> handleErroConversaoDadosExternos(ErroConversaoDadosExternosException ex, WebRequest request) {		
		List<Erro> erros = Arrays.asList(new Erro(
				messageSource.getMessage("erro.conversao.dados.externos", null, LocaleContextHolder.getLocale())));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			erros.add(new Erro(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
		}
			
		return erros;
	}
	
	
	public static class Erro {
		
		private String mensagem;
		
		public Erro(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}

	}
}
