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

import com.floradex.dto.PiantaDTO;
import com.floradex.dto.TipoDTO;
import com.floradex.entity.Pianta;
import com.floradex.entity.Tipo;
import com.floradex.service.PiantaService;
import com.floradex.service.TipoService;

@CrossOrigin
@RestController
public class TipoController {
	
	@Autowired
	private TipoService service; 

	@Autowired
	private PiantaService piantaService;

	@GetMapping("/api/tipo/all")
	public List<TipoDTO> getAll() {
		List<Tipo> tipi = service.getAllTipi();
		List<TipoDTO> tipiDTO= new ArrayList<>();
		for (Tipo tipo: tipi) {
			tipiDTO.add(new TipoDTO(tipo.getNome(), tipo.getDescrizione(), tipo.getFotopath()));
		}
		return tipiDTO;
	}

	@GetMapping("/api/tipo/{id}")
	public TipoDTO getTipoById(@PathVariable Integer id) {
		Tipo tipo = service.getTipoById(id);
		TipoDTO tipoDTO = new TipoDTO(tipo.getNome(), tipo.getDescrizione(), tipo.getFotopath());
		return tipoDTO;
	}
	
	@GetMapping("/api/tipo-piante/{id}")
	public List<PiantaDTO> getPianteByTipoId(@PathVariable Integer id) {
		Tipo tipo = service.getTipoById(id);
		List<PiantaDTO> tipoPiante = new ArrayList<>();
		for (Pianta pianta : tipo.getPiante()) {
			tipoPiante.add(new PiantaDTO(pianta.getNome(), pianta.getDescrizione(), pianta.getFotopath(), pianta.getTipo().getNome() ));
		}
		return tipoPiante;
	}

	@PutMapping("/api/tipo/enrolment/{id}/{idc}") 
	public PiantaDTO addPiantaToTipo(@PathVariable Integer id, @PathVariable Integer idc) { 
		Tipo tipo = service.getTipoById(id);
		Pianta pianta = piantaService.getPiantaById(idc);
		tipo.addPianta(pianta);
		service.updateTipo(id, tipo);
		return new PiantaDTO(pianta.getNome(), pianta.getFotopath(), pianta.getDescrizione(), pianta.getTipo().getNome()); 
	}

	@PostMapping("/admin/api/tipo/save") 
	public Tipo save(@RequestBody Tipo tipo) {
		return service.saveTipo(tipo);
	}

	@PutMapping("/admin/api/tipo/update/{id}")
	public Tipo update(@PathVariable Integer id, @RequestBody Tipo tipo) {
		return service.updateTipo(id, tipo);
	}

	@DeleteMapping("/admin/api/tipo/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteTipoById(id); 
	}
}
