package com.floradex.service;

import java.util.List;

import com.floradex.entity.Pianta;

public interface PiantaService {

	List<Pianta> getAllPiante();
	Pianta getPiantaById(Integer id);
	Pianta savePianta(Pianta tipo);
	Pianta updatePianta(Integer id, Pianta pianta);
	void deletePiantaById(Integer id);
	List<Pianta> getPianteByName(String nome);
}
