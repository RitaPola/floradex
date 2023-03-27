package com.floradex.service;

import java.util.List;


import com.floradex.entity.Ruolo;

public interface RuoloService {
	List<Ruolo> getAllRuoli();
	Ruolo getRuoloById(Integer id);
	Ruolo saveRuolo(Ruolo ruolo);
	Ruolo updateRuolo(Integer id, Ruolo ruolo);
	void deleteRuoloById(Integer id);
	Ruolo getRuoloByName(String nome);
}
