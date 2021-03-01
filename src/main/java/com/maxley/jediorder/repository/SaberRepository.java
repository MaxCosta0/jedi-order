package com.maxley.jediorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxley.jediorder.domain.Jedi;
import com.maxley.jediorder.domain.Saber;

@Repository
public interface SaberRepository extends JpaRepository<Saber, Long> {

	List<Saber> findByJedi(Jedi jedi);
}
