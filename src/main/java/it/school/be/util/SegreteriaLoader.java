package it.school.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.school.be.model.CorsoDiLaurea;
import it.school.be.model.Segreteria;
import it.school.be.model.Studente;

@Component
public class SegreteriaLoader implements CommandLineRunner {

	@Autowired
	ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {
		Segreteria segreteria = ctx.getBean(Segreteria.class);
		valorizzaSegreteria(segreteria);
	}

	public void valorizzaSegreteria(Segreteria segre) {
		CorsoDiLaurea c3 = CorsoDiLaurea.builder().codiceCorso("abcde").nomeCorso("economia aziendale")
				.indirizzoCorso("Via ciao 23").numeroEsami(11).build();
		CorsoDiLaurea c1 = CorsoDiLaurea.builder().codiceCorso("fghil").nomeCorso("economia delle imprese finanziare")
				.indirizzoCorso("Via ciaone 23").numeroEsami(11).build();

		Studente studente1 = Studente.builder().matricola("0123456").nome("luigi").cognome("verdi")
				.dataNascita("1993/04/18").email("ciao@gmail.com").indirizzo("via zatri 5").corsoLaura(c1).build();
		Studente studente2 = Studente.builder().matricola("01234567").nome("giacomo").cognome("leopardi")
				.dataNascita("1993/04/19").email("ciaociao@gmail.com").indirizzo("via zatri 8").corsoLaura(c3).build();
	segre.aggiungicorso(c1);
	segre.aggiungicorso(c3);
	segre.aggiungiStudente(studente2);
	segre.aggiungiStudente(studente1);
	}
	
}
