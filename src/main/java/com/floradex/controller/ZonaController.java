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
import com.floradex.dto.ZonaDTO;
import com.floradex.entity.Pianta;
import com.floradex.entity.Zona;
import com.floradex.service.PiantaService;
import com.floradex.service.ZonaService;

@CrossOrigin
@RestController
public class ZonaController {

	@Autowired
	private ZonaService service;
	
	@Autowired
	private PiantaService piantaService;
	
	@GetMapping("/api/zona/all")
	public List<ZonaDTO> getAll() {
		List<Zona> zone = service.getAllZone();
		List<ZonaDTO> zoneDTO= new ArrayList<>();
		for (Zona zona: zone) {
			zoneDTO.add(new ZonaDTO(zona.getNome(), zona.getDescrizione(), zona.getFotopath()));
		}
		return zoneDTO;
	}
	
	@GetMapping("/api/zona/{id}")
	public ZonaDTO getZonaById(@PathVariable Integer id) {
		Zona zona = service.getZonaById(id);
		ZonaDTO zonaDTO = new ZonaDTO(zona.getNome(), zona.getDescrizione(), zona.getFotopath());
		return zonaDTO;
	}
	
	@GetMapping("/api/piante-zone/{id}")
	public List<PiantaDTO> getPianteByZonaId(@PathVariable Integer id) {
		Zona zona = service.getZonaById(id);
		List<PiantaDTO> pianteZone = new ArrayList<>();
		for (Pianta pianta: zona.getPiante()) {
			pianteZone.add(new PiantaDTO(pianta.getNome(), pianta.getFotopath(), pianta.getDescrizione(), pianta.getTipo().getNome()));
		}
		return pianteZone;
	}
	
	@PutMapping("/api/zona/enrolment/{id}/{idc}") 
	public PiantaDTO addPiantaToZona(@PathVariable Integer id, @PathVariable Integer idc) { 
		Zona zona = service.getZonaById(id);
		Pianta pianta = piantaService.getPiantaById(idc);
		zona.addPianta(pianta);
		service.updateZona(id, zona);
		return new PiantaDTO(pianta.getNome(), pianta.getFotopath(), pianta.getDescrizione(),pianta.getTipo().getNome()); 
	}
	
	@PostMapping("/admin/api/zona/save") 
	public Zona save(@RequestBody Zona zona) {
		return service.saveZona(zona);
	}
	
	@PutMapping("/admin/api/zona/update/{id}")
	public Zona update(@PathVariable Integer id, @RequestBody Zona zona) {
		return service.updateZona(id, zona);
	}
	
	@DeleteMapping("/admin/api/zona/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteZonaById(id); 
	}
	
}