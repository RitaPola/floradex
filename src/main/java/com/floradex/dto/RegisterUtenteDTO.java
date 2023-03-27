package com.floradex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUtenteDTO {
	
	private String nome;
	private String cognome;
	private String email;
	private String password;

}
