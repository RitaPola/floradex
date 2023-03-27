package com.floradex.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.floradex.entity.Utente;
import com.floradex.exception.UserAlreadyExistsException;
import com.floradex.repository.UtenteRepository;
@Service
public class UtenteServiceImpl implements UtenteService{
	@Autowired
	private UtenteRepository repository;

	@Override
	public List<Utente> getAllUtenti() {
		Iterable<Utente> listOfTipi = repository.findAll();
		List<Utente> tipi = (List<Utente>) listOfTipi;
		// return (Set<Utente>) listOfTipi;
		return tipi;
	}

	@Override
	public Utente getUtenteById(Integer id) {
		Optional<Utente> optUtente = repository.findById(id);
		Utente utente = new Utente();
		if (!optUtente.isEmpty()) {
			utente = optUtente.get();
		}
		return utente;
	}
	@Override
	public Utente getUtenteByEmail(String email) throws NotFoundException {
		Optional<Utente> optUtente = repository.findByEmail(email);	;
		if(!optUtente.isEmpty()) {
			throw new NotFoundException();	

		}
		return optUtente.get();
	}

	@Override
	public Utente saveUtente(Utente utente) throws UserAlreadyExistsException{
		Optional<Utente> optUtente = repository.findByEmail(utente.getEmail());
		if(!optUtente.isEmpty()) {
			throw new UserAlreadyExistsException("L'utente esiste");
		}
		return repository.save(utente);
	}

	@Override
	public Utente updateUtente(Integer id, Utente utente) {
		Optional<Utente> optUtente = repository.findById(id);
		Utente u = new Utente();
		if (!optUtente.isEmpty()) {
			u = optUtente.get();
			u.setNome(utente.getNome());
			u.setCognome(utente.getCognome());
			u.setEmail(utente.getEmail());
			u.setPassword(utente.getPassword());
			u.setPiante(utente.getPiante());
			u.setRuoli(utente.getRuoli());
			repository.save(u);
		}
		return u;
	}

	@Override
	public void deleteUtenteById(Integer id) {
		repository.deleteById(id);
	}


}
