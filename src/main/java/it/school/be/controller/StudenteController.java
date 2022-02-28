
package it.school.be.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.school.be.model.CorsoDiLaurea;
import it.school.be.model.Segreteria;
import it.school.be.model.Studente;

@Controller
//@RequestMapping("/studente")
public class StudenteController {

	@Autowired
	ApplicationContext ctx;

	private Segreteria getSegreteria() {
		return ctx.getBean(Segreteria.class);
	}

	@GetMapping("/visualizzastudenti")
	public ModelAndView visualizzaStudenti() {
		Segreteria segre = getSegreteria();
		return new ModelAndView("visualizzaStudenti", "studente", segre);
	}

	@GetMapping("/visualizzacorsi")
	public ModelAndView visualizzaCorsi() {
		Segreteria segre = getSegreteria();
		List<CorsoDiLaurea> corsi = segre.getDatabaseCorso();
		return new ModelAndView("visualizzaCorsi", "corsi", corsi);
	}

	@GetMapping("/formstudente")
	public ModelAndView formStudenti() {
		Segreteria segre = getSegreteria();
		List<CorsoDiLaurea> corsi = segre.getDatabaseCorso();
		ModelAndView mev = new ModelAndView("aggiungistudente", "studente", new Studente());
		mev.addObject("corsi", corsi);
		return mev;
	}

	@PostMapping("/aggiungistudente")
	public ModelAndView aggiungiStudente(Studente studente, BindingResult result, Model model) {
		Segreteria segre = getSegreteria();
		if(result.hasErrors()) {
			String msg = "***ERRORE*** Studente non inserito";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		Studente studente1 = segre.studenteByMatricola(studente.getMatricola());
		if(studente1!=null) {
			if(studente1.getMatricola().equals(studente.getMatricola())) {
				String msg = "***ERRORE*** Attezione lo studente che vuoi inserire è gia nella lista";
				ModelAndView mv = new ModelAndView("error", "msg", msg);
				return mv;
			}	
		}
		segre.aggiungiStudente(studente);
		return visualizzaStudenti();
	}

	@GetMapping("/formcorso")
	public ModelAndView formCorso() {
		return new ModelAndView("aggiungicorso", "corsi", new CorsoDiLaurea());
	}

	@PostMapping("/aggiungicorso")
	public ModelAndView aggiungicorso(CorsoDiLaurea corso, BindingResult result, Model model) {
		Segreteria segre = getSegreteria();
		if(result.hasErrors()) {
			String msg = "***ERRORE*** Corso non inserito";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		CorsoDiLaurea c = segre.corsoByCodice(corso.getCodiceCorso());
		if(c!=null) {
			if(c.getCodiceCorso().equals(corso.getCodiceCorso())) {
				String msg = "***ERRORE*** attenzione il corso che vuoi inserire è presente nella lista dei crosi";
				ModelAndView mv = new ModelAndView("error", "msg", msg);
				return mv;
			}	
		}
		segre.aggiungicorso(corso);
		return visualizzaCorsi();
	}

	@GetMapping("/formaggiornastudente/{matricola}")
	public ModelAndView formAggiornaStudente(@PathVariable("matricola") String matricola) {
		Segreteria segre = getSegreteria();
		Studente s = segre.studenteByMatricola(matricola);
		List<CorsoDiLaurea> corso = segre.getDatabaseCorso();
		ModelAndView mev = new ModelAndView("aggiornastudente", "studente", new Studente());
		mev.addObject("studente", s);
		mev.addObject("corso", corso);
		return mev;
	}

	@PostMapping("/aggiornastudente/{matricola}")
	public ModelAndView aggiornaStudente(  Studente studente,
			@PathVariable String matricola, BindingResult result) {
		Segreteria segre = getSegreteria();
		if(result.hasErrors()) {
			String msg = "Errore nell'aggiorna Studente";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		studente.setMatricola(matricola);
		segre.eliminaStudente(studente.getMatricola());
		segre.aggiungiStudente(studente);
		return visualizzaStudenti();

	}

	@GetMapping("/formaggiornacorso/{codice}")
	public ModelAndView formAggiornaCorso(@PathVariable("codice") String codice) {
		Segreteria segre = getSegreteria();
		CorsoDiLaurea corso = segre.corsoByCodice(codice);
		ModelAndView mev = new ModelAndView("aggiornacorso", "corsi", new CorsoDiLaurea());
		mev.addObject("corsi", corso);
		return mev;
	}

	@PostMapping("/aggiornacorso/{codice}")
	public ModelAndView aggiornaCorso( @PathVariable String codice, CorsoDiLaurea corso,
			BindingResult result) {
		Segreteria segre = getSegreteria();
		if(result.hasErrors()) {
			String msg = "Errore nell'aggiorna Studente";
			ModelAndView mv = new ModelAndView("error", "msg", msg);
			return mv;
		}
		corso.setCodiceCorso(codice);
		segre.eliminaCorso(corso.getCodiceCorso());
		segre.aggiungicorso(corso);
		return visualizzaCorsi();
	}

	@GetMapping("/cancellastudente/{matricola}")
	public ModelAndView cancellaStudente(@PathVariable String matricola, Model model) {
		Segreteria segre = getSegreteria();
		segre.eliminaStudente(matricola);
		return visualizzaStudenti();
	}

	@GetMapping("/cancellacorso/{codice}")
	public ModelAndView cancellaCorso(@PathVariable String codice,Model model) {
		Segreteria segre = getSegreteria();
		segre.eliminaCorso(codice);
		return visualizzaCorsi();

	}

}
