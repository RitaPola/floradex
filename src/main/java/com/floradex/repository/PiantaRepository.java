package com.floradex.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.floradex.entity.Pianta;

public interface PiantaRepository extends CrudRepository <Pianta , Integer> {

	@Query(value = "SELECT * FROM Piante WHERE nome LIKE %?1%", nativeQuery = true)
	Iterable<Pianta> getPianteByName(@Param("nome") String nome);
	
}
