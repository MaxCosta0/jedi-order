package com.maxley.jediorder.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maxley.jediorder.domain.Jedi;
import com.maxley.jediorder.domain.JediPlusSaber;
import com.maxley.jediorder.domain.Saber;
import com.maxley.jediorder.exception.DomainException;
import com.maxley.jediorder.service.JediService;
import com.maxley.jediorder.service.SaberService;

@RestController
@RequestMapping("/jedi")		//Jedi is already a plural 
public class JediController {

	@Autowired
	private JediService jediService;
	
	@Autowired
	private SaberService saberService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Jedi postJedi(@Valid @RequestBody Jedi jedi) {
		return jediService.saveJedi(jedi);
	}
	
	@GetMapping
	public List<Jedi> getAllJedi(){
		return jediService.findAllJedi();
	}
	
	@GetMapping("/{jediName}")
	public ResponseEntity<JediPlusSaber> getOneJedi(@PathVariable String jediName){
		Optional<Jedi> jedi= jediService.findJedi(jediName);
		
		if(jedi.isEmpty()) {
			throw new DomainException("there's no Jedi with this name.");		//throw exception
		}
		
		List<Saber> sabers = saberService.findAllSaberFromJedi(jediName);
		JediPlusSaber jediPlusSaber = new JediPlusSaber(jedi.get(), sabers);
		
		return ResponseEntity.ok(jediPlusSaber);
	}
	
	@PutMapping("/{jediName}")
	public ResponseEntity<Jedi> putJedi(@Valid @RequestBody Jedi jedi, @PathVariable String jediName) {
		Optional<Jedi> foundJedi = jediService.findJedi(jediName);
		
		if(foundJedi.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		jedi.setId(foundJedi.get().getId());
		jedi = jediService.saveJedi(jedi);
		
		return ResponseEntity.ok(jedi);
	}
	
	@DeleteMapping("/{jediName}")
	public ResponseEntity<Void> deleteJedi(@PathVariable String jediName){
		Boolean ok = jediService.removeJedi(jediName);
		if(ok) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
