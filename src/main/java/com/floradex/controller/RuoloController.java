package com.floradex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.floradex.dto.RuoloDTO;
import com.floradex.dto.UtenteDTO;
import com.floradex.entity.Pianta;
import com.floradex.entity.Ruolo;
import com.floradex.entity.Utente;
import com.floradex.service.RuoloService;
import com.floradex.service.UtenteService;

@CrossOrigin
@RestController
public class RuoloController {

	@Autowired
	private RuoloService service;

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/api/ruolo/all")
	public List<RuoloDTO> getAllRuoli() {
		List<Ruolo> ruoli = service.getAllRuoli();
		List<RuoloDTO> ruoliDTO= new ArrayList<>();
		for (Ruolo ruolo: ruoli) {
			ruoliDTO.add(new RuoloDTO(ruolo.getNome()));
		}
		return ruoliDTO;
	}

	@GetMapping("/api/ruolo/{id}")
	public RuoloDTO getRuoloById(@PathVariable Integer id) {
		Ruolo ruolo = service.getRuoloById(id);
		RuoloDTO ruoloDTO = new RuoloDTO(ruolo.getNome());
		return ruoloDTO;
	}

	@GetMapping("/api/utenti-ruoli/{id}")
	public List<UtenteDTO> getUtentiByRuoloId(@PathVariable Integer id) {
		Ruolo ruolo = service.getRuoloById(id);
		List<UtenteDTO> utentiRuoli = new ArrayList<>();
		for (Utente utente: ruolo.getUtenti()) {
			utentiRuoli.add(new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante()));	
		}
		return utentiRuoli;
	}

	@PutMapping("/api/ruolo/enrolment/{id}/{idc}") 
	public UtenteDTO addUtenteToRuolo(@PathVariable Integer id, @PathVariable Integer idc) { 
		Ruolo ruolo = service.getRuoloById(id);
		Utente utente = utenteService.getUtenteById(idc);
		ruolo.addUtente(utente);
		service.updateRuolo(id, ruolo);
		return new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante());	
	}

	@PostMapping("/admin/api/ruolo/save") 
	public Ruolo save(@RequestBody Ruolo ruolo) {
		return service.saveRuolo(ruolo);
	}

	@PutMapping("/admin/api/ruolo/update/{id}")
	public Ruolo update(@PathVariable Integer id, @RequestBody Ruolo ruolo) {
		return service.updateRuolo(id, ruolo);
	}

	@DeleteMapping("/admin/api/ruolo/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteRuoloById(id); 
	}


}
