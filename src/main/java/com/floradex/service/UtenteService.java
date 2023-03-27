package com.floradex.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.floradex.entity.Utente;
import com.floradex.exception.UserAlreadyExistsException;

public interface UtenteService {

	List<Utente> getAllUtenti();
	Utente getUtenteById(Integer id);
	
	Utente saveUtente(Utente utente) throws UserAlreadyExistsException;
	Utente updateUtente(Integer id, Utente tipo);
	void deleteUtenteById(Integer id);
	Utente getUtenteByEmail(String email) throws NotFoundException;


}
