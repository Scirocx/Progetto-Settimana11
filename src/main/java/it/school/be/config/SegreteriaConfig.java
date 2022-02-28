package it.school.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.school.be.model.CorsoDiLaurea;
import it.school.be.model.Segreteria;
import it.school.be.model.Studente;

@Configuration
public class SegreteriaConfig {
	
	@Bean
	public Segreteria segreteria() {
		return new Segreteria();
	}
	
	@Bean
	@Scope("prototype")
	public Studente studente() {
		return new Studente();
	}
	
	@Bean
	@Scope("prototype")
	public CorsoDiLaurea corsoLaurea() {
		return new CorsoDiLaurea();
	}

}
