package it.school.be.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Segreteria {

	private List<Studente> databaseStudenti = new ArrayList<Studente>();
	private List<CorsoDiLaurea> databaseCorso = new ArrayList<CorsoDiLaurea>();

	public boolean aggiungicorso(CorsoDiLaurea s) {
		if (databaseCorso.contains(s))
			return false;
		databaseCorso.add(s);
		return true;
	}

	public boolean aggiungiStudente(Studente s) {
		if (databaseStudenti.contains(s))
			return false;
		databaseStudenti.add(s);
		return true;
	}

	public boolean eliminaStudente(String matricola) {
		for (Studente s : databaseStudenti) {
			if (s.getMatricola().equals(matricola)) {
				databaseStudenti.remove(s);
				return true;
			}

		}
		return false;

	}

	public boolean eliminaCorso(String codiceCorso) {
		for (CorsoDiLaurea corso : databaseCorso) {
			if (corso.getCodiceCorso().equals(codiceCorso))
				databaseCorso.remove(corso);
			return true;
		}

		return false;
	}

	public Studente studenteByMatricola(String matricola) {
		for (Studente s : databaseStudenti) {
			if (s.getMatricola().equals(matricola))
				return s;
		}
		return null;
	}

	public CorsoDiLaurea corsoByCodice(String codice) {
		for (CorsoDiLaurea c : databaseCorso) {
			if (c.getCodiceCorso().equals(codice))
				return c;
		}
		return null;
	}

}
