package com.floradex.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.floradex.entity.Utente;

public interface UtenteRepository extends CrudRepository <Utente , Integer>{

	Optional<Utente> findByEmail(String email);
	
	@Query(value = "SELECT * FROM Utenti WHERE email LIKE %?1%", nativeQuery = true)
	Iterable<Utente> getUtentiByName(@Param("email") String email);

	

}
