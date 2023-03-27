package com.floradex.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floradex.entity.Pianta;
import com.floradex.repository.PiantaRepository;

@Service
public class PiantaServiceImpl implements PiantaService {

	@Autowired
	private PiantaRepository repository;
	
	@Override
	public List<Pianta> getAllPiante() {
		Iterable<Pianta> piante = repository.findAll();
		return (List<Pianta>) piante;
	}

	@Override
	public Pianta getPiantaById(Integer id) {
		Optional<Pianta> optPianta = repository.findById(id);
		Pianta pianta = new Pianta();
		if (!optPianta.isEmpty()) {
			pianta = optPianta.get();
		}
		return pianta;
	}

	@Override
	public Pianta savePianta(Pianta pianta) {
		return repository.save(pianta);
	}

	@Override
	public Pianta updatePianta(Integer id, Pianta pianta) {
		Optional<Pianta> optPianta = repository.findById(id);
		Pianta p = new Pianta();
		if (!optPianta.isEmpty()) {
			p = optPianta.get();
			p.setNome(pianta.getNome());
			p.setDescrizione(pianta.getDescrizione());
			p.setFotopath(pianta.getFotopath());
			p.setTipo(pianta.getTipo());
			p.setUtenti(pianta.getUtenti());
			p.setZone(pianta.getZone());
			repository.save(p);
		}
		return p;
	}

	@Override
	public void deletePiantaById(Integer id) {
		repository.deleteById(id);
	}
	
	
	@Override
	public List<Pianta> getPianteByName(String nome){
		Iterable<Pianta> piante = repository.getPianteByName(nome);
		return (List<Pianta>) piante;
	}
	
	
	
}

