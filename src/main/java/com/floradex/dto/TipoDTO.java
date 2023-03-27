package com.floradex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDTO {
	
	private String tipoNome;
	private String tipoDescrizione;
	private String tipoFotopath;

}
