package com.floradex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floradex.entity.Tipo;
import com.floradex.repository.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService{

	@Autowired
	private TipoRepository repository;

	@Override
	public List<Tipo> getAllTipi() {
		Iterable<Tipo> listOfTipi = repository.findAll();
		List<Tipo> tipi = (List<Tipo>) listOfTipi;
		// return (Set<Tipo>) listOfTipi;
		return tipi;
	}

	@Override
	public Tipo getTipoById(Integer id) {
		Optional<Tipo> optTipo = repository.findById(id);
		Tipo tipo = new Tipo();
		if (!optTipo.isEmpty()) {
			tipo = optTipo.get();
		}
		return tipo;
	}

	@Override
	public Tipo saveTipo(Tipo tipo) {
		return repository.save(tipo);
	}

	@Override
	public Tipo updateTipo(Integer id, Tipo tipo) {
		Optional<Tipo> optTipo = repository.findById(id);
		Tipo t = new Tipo();
		if (!optTipo.isEmpty()) {
			t = optTipo.get();
			t.setNome(tipo.getNome());
			t.setDescrizione(tipo.getDescrizione());
			t.setFotopath(tipo.getFotopath());
			t.setPiante(tipo.getPiante());
			repository.save(t);
		}
		return t;
	}

	@Override
	public void deleteTipoById(Integer id) {
		repository.deleteById(id);
	}

}
