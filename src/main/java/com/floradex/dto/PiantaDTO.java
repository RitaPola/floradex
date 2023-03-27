package com.floradex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PiantaDTO {
	
	private String piantaNome;
	private String piantaDescrizione;
	private String piantaFotopath;
	private String piantaTipoNome;

}
