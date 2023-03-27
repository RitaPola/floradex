package com.floradex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floradex.entity.Ruolo;
import com.floradex.repository.RuoloRepository;
@Service
public class RuoloServiceImpl implements RuoloService{
	@Autowired
	private RuoloRepository repository;

	@Override
	public List<Ruolo> getAllRuoli() {
		Iterable<Ruolo> ruoli = repository.findAll();
		return (List<Ruolo>) ruoli;
	}

	@Override
	public Ruolo getRuoloById(Integer id) {
		Optional<Ruolo> optRuolo = repository.findById(id);
		Ruolo ruolo = new Ruolo();
		if (!optRuolo.isEmpty()) {
			ruolo = optRuolo.get();
		}
		return ruolo;
	}
	@Override
	public Ruolo getRuoloByName(String nome) {
		return repository.findByNome(nome).get();
	}

	@Override
	public Ruolo saveRuolo(Ruolo ruolo) {
		return repository.save(ruolo);
	}

	@Override
	public Ruolo updateRuolo(Integer id, Ruolo ruolo) {
		Optional<Ruolo> optRuolo = repository.findById(id);
		Ruolo r = new Ruolo();
		if (!optRuolo.isEmpty()) {
			r = optRuolo.get();
			r.setNome(ruolo.getNome());
			r.setUtenti(ruolo.getUtenti());
			repository.save(r);
		}
		return r;
	}

	@Override
	public void deleteRuoloById(Integer id) {
		repository.deleteById(id);
	}
}




