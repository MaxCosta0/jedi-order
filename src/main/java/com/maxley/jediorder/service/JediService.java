package com.maxley.jediorder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxley.jediorder.domain.Jedi;
import com.maxley.jediorder.repository.JediRepository;

@Service
public class JediService {
	
	@Autowired
	private JediRepository jediRepository;
	
	public Jedi saveJedi(Jedi jedi) {
		Optional<Jedi> jediFound = jediRepository.findByName(jedi.getName());
		
		if(jediFound.isPresent() && !jedi.equals(jediFound.get())) {
			return jediFound.get();
		}
		
		return jediRepository.save(jedi);
	}
	
	public Optional<Jedi> findJedi(String name) {
		return jediRepository.findByName(name);
	}
	
	public List<Jedi> findAllJedi(){
		return jediRepository.findAll();
	}
	
	public Boolean removeJedi(String name) {
		return jediRepository.deleteByName(name);
	}
}
