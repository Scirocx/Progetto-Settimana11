package it.school.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.school.be.model.CorsoDiLaurea;
import it.school.be.model.Segreteria;

@Component
public class CorsoController implements Converter<String, CorsoDiLaurea>{

	@Autowired
	ApplicationContext ctx;
	
	@Override
	public CorsoDiLaurea convert(String codice) {
		Segreteria segre = ctx.getBean(Segreteria.class);
		return segre.corsoByCodice(codice);
	}

	

}
