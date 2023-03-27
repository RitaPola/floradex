package com.floradex.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.floradex.entity.Ruolo;

public interface RuoloRepository extends CrudRepository <Ruolo , Integer>{
		Optional<Ruolo> findByNome(String nome);
}
