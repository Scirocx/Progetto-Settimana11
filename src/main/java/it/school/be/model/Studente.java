package it.school.be.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Studente {
	@NotBlank
	private String matricola;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dataNascita;
	@NotBlank
	private String email;
	@NotBlank
	private String indirizzo;
	@NotBlank
	private String citta;
	@NotBlank
	private CorsoDiLaurea corsoLaura;

	
	
}
