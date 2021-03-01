package com.maxley.jediorder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxley.jediorder.domain.Jedi;

@Repository
public interface JediRepository extends JpaRepository<Jedi, Long>{
	Optional<Jedi> findByName(String name);
	
	Boolean deleteByName(String name);
}
