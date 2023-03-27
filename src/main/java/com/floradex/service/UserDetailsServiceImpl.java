package com.floradex.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.floradex.entity.Utente;
import com.floradex.repository.UtenteRepository;
import com.floradex.security.JwtUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UtenteRepository repository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utente> optUtente = repository.findByEmail(username);
		if(optUtente.isEmpty()) {
			throw new UsernameNotFoundException("Utente non trovato");
		}
		return new JwtUser(optUtente.get());
	}



}
