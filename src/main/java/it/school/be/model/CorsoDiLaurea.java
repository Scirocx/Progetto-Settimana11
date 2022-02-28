package it.school.be.model;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorsoDiLaurea {
	@NotBlank
	private String codiceCorso;
	@NotBlank
	private String nomeCorso;
	@NotBlank
	private String indirizzoCorso;
	@Min(10)
	private Integer numeroEsami;
	
	
	
	

}
