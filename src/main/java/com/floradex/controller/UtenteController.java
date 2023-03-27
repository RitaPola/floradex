package com.floradex.controller;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.floradex.dto.AuthResponseDTO;
import com.floradex.dto.LoginUtenteDTO;
import com.floradex.dto.PiantaDTO;
import com.floradex.dto.RegisterUtenteDTO;
import com.floradex.dto.UtenteDTO;
import com.floradex.entity.Pianta;
import com.floradex.entity.Ruolo;
import com.floradex.entity.Utente;
import com.floradex.exception.UserAlreadyExistsException;
import com.floradex.security.JwtGenerator;
import com.floradex.service.PiantaService;
import com.floradex.service.RuoloService;
import com.floradex.service.UtenteService;


import lombok.extern.java.Log;


@CrossOrigin
@RestController
@Log
public class UtenteController {

	@Autowired
	private UtenteService service;

	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private PiantaService piantaService;

	@Autowired 
	private PasswordEncoder passwordEncoder; 

	@Autowired 
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtGenerator jwtGenerator;


	@PostMapping("/api/auth/signup")
	public ResponseEntity<String> utenteSignup(@RequestBody RegisterUtenteDTO utenteDTO){

		//MAPPING UtenteDTO -> Utente
		log.info("Registrazione di un nuovo utente");
		Utente utente = new Utente();
		utente.setNome(utenteDTO.getNome());
		utente.setCognome(utenteDTO.getCognome());
		utente.setEmail((utenteDTO).getEmail());
		log.info("Encode della password");
		utente.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
		//aggiunta del ruolo USER
		log.info("AGGIUNTA DEL RUOLO USER");
		Ruolo ruolo = ruoloService.getRuoloByName("USER");
		utente.addRuolo(ruolo);
		log.info("utente creato : " + utente);

		try {
			Utente newUtente = service.saveUtente(utente);
			String info = String.format("Inserimento utente %s eseguito con successo" , newUtente.getIdUtente());
			return new ResponseEntity<String> (info , HttpStatus.OK);
		} catch(UserAlreadyExistsException e ) {
			String info = String.format("Inserimento utente non eseguito a causa di %s", e.getMessaggio());
			return new ResponseEntity<String>(info , HttpStatus.BAD_REQUEST);
		}	
	}

	@PostMapping ("/api/auth/login") 
	public ResponseEntity<AuthResponseDTO> userLogin(@RequestBody LoginUtenteDTO 
			utenteDTO) { 
		try { 
			Authentication authentication = 
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( 
							utenteDTO.getEmail(), utenteDTO.getPassword())); 

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String Token = jwtGenerator.generateToken(authentication);
			AuthResponseDTO authResponseDTO = new AuthResponseDTO(Token);
			return new ResponseEntity<>(authResponseDTO , HttpStatus.OK);
		} catch (AuthenticationException e ) {
			return new ResponseEntity<>(new AuthResponseDTO(), HttpStatus.UNAUTHORIZED);
		}
	}



	@GetMapping("/api/utente/all")
	public List<UtenteDTO> getAll() {
		List<Utente> utenti = (List<Utente>) service.getAllUtenti();
		List<UtenteDTO> utentiDTO= new ArrayList<>();
		for (Utente utente: utenti) {
			utentiDTO.add(new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante()));	
		}
		return utentiDTO;
	}

//	@PutMapping("/api/utente/enrolment/{id}/{idc}") 
//	public PiantaDTO addPiantaToUtente(@PathVariable Integer id, @PathVariable Integer idc) { 
//		Utente utente = service.getUtenteById(id);
//		Pianta pianta = piantaService.getPiantaById(idc);
//		utente.addPianta(pianta);
//		service.updateUtente(id, utente);
//		return new PiantaDTO(pianta.getNome(), pianta.getFotopath(), pianta.getDescrizione(), pianta.getTipo().getNome()); 
//	}
	
	@RequestMapping(value = "/api/utenti_piante/add/{id}/{idc}", method = RequestMethod.POST, 
            headers = "Accept=application/json")
	public UtenteDTO addPiantaToUtente(@PathVariable Integer id, @PathVariable Integer idc) {
		Pianta pianta = piantaService.getPiantaById(id);
		Utente utente = service.getUtenteById(idc);
		utente.addPianta(pianta);
		service.updateUtente(id, utente);
		return new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante());	
	}
	
	
	@GetMapping("/api/utente/{id}")
	public UtenteDTO getUtenteById(@PathVariable Integer id) {
		Utente utente = service.getUtenteById(id);
		UtenteDTO utenteDTO =new UtenteDTO(utente.getIdUtente(), utente.getNome(), utente.getCognome(), utente.getEmail(), (List<Pianta>) utente.getPiante());	
		return utenteDTO;
	}

//	@GetMapping("/api/utente-username/{email}")
//	public List<UtenteDTO>getUtenteByUsername(@PathVariable String email) {
//	Utente utente = service.getUtenteByEmail(email);
//		
//	}


	@PostMapping("/admin/api/utente/save") 
	public Utente save(@RequestBody Utente utente) throws UserAlreadyExistsException {
		return service.saveUtente(utente);
	}

	@PutMapping("/admin/api/utente/update/{id}")
	public Utente update(@PathVariable Integer id, @RequestBody Utente utente) {
		return service.updateUtente(id, utente);
	}

	@DeleteMapping("/admin/api/utente/delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteUtenteById(id); 
	}

}


