package com.floradex.dto;

import java.util.List;

import com.floradex.entity.Pianta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {
	
	private Integer utenteIdUtente;
	private String utenteNome;
	private String utenteCognome;
	private String utenteEmail;
	private List<Pianta> utentePiante;
	
}
