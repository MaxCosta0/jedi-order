package com.maxley.jediorder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxley.jediorder.domain.Jedi;
import com.maxley.jediorder.domain.Saber;
import com.maxley.jediorder.repository.SaberRepository;

@Service
public class SaberService {

	@Autowired
	private SaberRepository saberRepository;
	
	@Autowired
	private JediService jediService;
	
	public Saber saveSaber(Saber saber) {
		Jedi jedi = saber.getJedi();
		
		Jedi savedJedi =  jediService.saveJedi(jedi);
		
		Saber newSaber = new Saber(null, saber.getColor(), savedJedi);
		
		return saberRepository.save(newSaber);
	}
	
	public List<Saber> findAllSaberFromJedi(String name){
		Optional<Jedi> jedi = jediService.findJedi(name);
		
		if(jedi.isEmpty()) {
			return null;
		}
		
		return saberRepository.findByJedi(jedi.get());
	}
	
	public Optional<Saber> findOneSaber(Long id){
		return saberRepository.findById(id);
	}
	
	public Boolean removeSaber(Long id) {
		Optional<Saber> saberToRemove = saberRepository.findById(id);
		
		if(saberToRemove.isEmpty()) {
			return false;
		}
		
		saberRepository.delete(saberToRemove.get());
		return true;
	}
}
