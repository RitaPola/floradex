package com.floradex.service;

import java.util.List;

import com.floradex.entity.Tipo;

public interface TipoService {
	List<Tipo> getAllTipi();
	Tipo getTipoById(Integer id);
	Tipo saveTipo(Tipo tipo);
	Tipo updateTipo(Integer id, Tipo tipo);
	void deleteTipoById(Integer id);

}
