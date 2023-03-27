package com.floradex.controller;

import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
import com.floradex.dto.UtenteDTO;
import com.floradex.entity.Pianta;
import com.floradex.entity.Tipo;
import com.floradex.entity.Utente;
import com.floradex.service.PiantaService;
import com.floradex.service.TipoService;
import com.floradex.service.UtenteService;

import java.util.Base64;


//MODIFICHE
@CrossOrigin
@RestController
public class PiantaController {

	@Autowired
	private PiantaService service; 

	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/api/pianta/all")
	public List<PiantaDTO> getAll() {
			List<Pianta> piante = (List<Pianta>) service.getAllPiante();
			List<PiantaDTO> pianteDTO= new ArrayList<>();
			for (Pianta pianta: piante) {
				pianteDTO.add(new PiantaDTO(pianta.getNome(), pianta.getDescrizione(), pianta.getFotopath(), pianta.getTipo().getNome()));
			}
			return pianteDTO;
	}
	
	@GetMapping("/api/pianta/nome/{nome}")
	public List<PiantaDTO> getPianteByName(@PathVariable String nome) {
			List<Pianta> piante= service.getPianteByName(nome);
			List<PiantaDTO> pianteDTO= new ArrayList<>();
			for (Pianta pianta: piante) {
				pianteDTO.add(new PiantaDTO(pianta.getNome(), pianta.getDescrizione(), pianta.getFotopath(), pianta.getTipo().getNome()));
			}
			return pianteDTO;
	}
	

	
	@RequestMapping(value = "/api/pianta/foto/{fotopath}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String fotopath) throws IOException {

        var imgFile = new ClassPathResource("images/piante/" + fotopath);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
    
    
    
    @GetMapping("/api/pianta/fotob64/{fotopath}")
	public String getPiantaFoto(@PathVariable String fotopath) throws IOException {
        var imgFile = new ClassPathResource("images/piante/" + fotopath);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        String b64 = Base64.getEncoder().encodeToString(bytes);
        return b64;		
	}
    

	@GetMapping("/api/pianta/{id}")
	public PiantaDTO getPiantaById(@PathVariable Integer id) {
		Pianta pianta = service.getPiantaById(id);
		PiantaDTO piantaDTO = new PiantaDTO(pianta.getNome(), pianta.getDescrizione(),pianta.getFotopath(), pianta.getTipo().getNome());
		return piantaDTO;
	}
	
	@RequestMapping(value = "/api/pianteutenti/add/{idPianta}/{idUtente}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
	public void addPiantaToUtente(@PathVariable Integer idUtente, @PathVariable Integer idPianta) {
		Pianta pianta = service.getPiantaById(idPianta);
		Utente utente = utenteService.getUtenteById(idUtente);
		pianta.addUtente(utente);
		service.updatePianta(idPianta, pianta);
		//return new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante());	
	}


//	@PutMapping("/api/pianta/enrolment/{id}/{idc}") 
//	public TipoDTO addTipoToPianta(@PathVariable Integer id, @PathVariable Integer idc) { 
//		Pianta pianta = service.getPiantaById(id);
//		Tipo tipo = tipoService.getTipoById(idc);
//		pianta.setTipo(tipo);
//		service.updatePianta(id, pianta);
//		return new TipoDTO(tipo.getNome(), tipo.getDescrizione(),tipo.getFotopath()); 
//	}

	@PostMapping("/admin/api/pianta/save") 
	public Pianta save(@RequestBody Pianta pianta) {
		return service.savePianta(pianta);
	}

	@PutMapping("/admin/api/pianta/update/{id}")
	public Pianta update(@PathVariable Integer id, @RequestBody Pianta pianta) {
		return service.updatePianta(id, pianta);
	}

	@DeleteMapping("/admin/api/pianta/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deletePiantaById(id); 
	}
	
}
