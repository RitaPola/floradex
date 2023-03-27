package com.floradex.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.floradex.entity.Zona;
import com.floradex.repository.ZonaRepository;

@Service
public class ZonaServiceImpl implements ZonaService {
	@Autowired
	private ZonaRepository repository;

	@Override
	public List<Zona> getAllZone() {
		Iterable<Zona> listOfZone = repository.findAll();
		List<Zona> zone = (List<Zona>) listOfZone;
		// return (List<Zona>) listOfZonas;
		return zone;
	}

	@Override
	public Zona getZonaById(Integer id) {
		Optional<Zona> optZona = repository.findById(id);
		Zona zona = new Zona();
		if (!optZona.isEmpty()) {
			zona = optZona.get();
		}
		return zona;
	}

	@Override
	public Zona saveZona(Zona zona) {
		return repository.save(zona);
	}

	@Override
	public Zona updateZona(Integer id, Zona zona) {
		Optional<Zona> optZona = repository.findById(id);
		Zona z = new Zona();
		if (!optZona.isEmpty()) {
			z = optZona.get();
			z.setNome(zona.getNome());
			z.setFotopath(zona.getFotopath());
			z.setDescrizione(zona.getDescrizione());
			z.setPiante(zona.getPiante());
			repository.save(z);
		}
		return z;
	}

	@Override
	public void deleteZonaById(Integer id) {
		repository.deleteById(id);
	}

}
