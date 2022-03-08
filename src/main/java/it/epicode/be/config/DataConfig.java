package it.epicode.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.be.model.ArchivioDati;
import it.epicode.be.model.Studente;



@Configuration
public class DataConfig {

	@Bean
	public ArchivioDati archivioDati() {
		return new ArchivioDati();
	}
	
	@Bean
	@Scope("prototype")
	public Studente studente() {
		return new Studente();
	}
}
