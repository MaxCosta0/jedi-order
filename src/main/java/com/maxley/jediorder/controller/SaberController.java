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

import com.maxley.jediorder.domain.Saber;
import com.maxley.jediorder.exception.DomainException;
import com.maxley.jediorder.service.SaberService;

@RestController
@RequestMapping("/sabers")
public class SaberController {

	@Autowired
	private SaberService saberService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Saber postSaber(@Valid @RequestBody Saber saber) {
		return saberService.saveSaber(saber);
	}
	
	@GetMapping("/{jediName}")
	public List<Saber> getSabersFromJedi(@PathVariable String jediName) {

		List<Saber> sabers = saberService.findAllSaberFromJedi(jediName);
		
		if(sabers == null) {
			throw new DomainException("there's no jedi with this name");
		}
		
		return sabers;
	}
	
	@PutMapping("/{saberId}")
	public ResponseEntity<Saber> putSaber(@Valid @RequestBody Saber newSaber, @PathVariable Long saberId) {
		Optional<Saber> foundSaber = saberService.findOneSaber(saberId);
		
		if(foundSaber.isEmpty()) {
			throw new DomainException("there's no saber with this id");
		}
		
		newSaber.setId(foundSaber.get().getId());
		newSaber = saberService.saveSaber(newSaber);
		
		return ResponseEntity.ok(newSaber);
		
	}
	
	@DeleteMapping("/{saberId}")
	public ResponseEntity<Void> deleteSaber(@PathVariable Long saberId){
		Boolean success = saberService.removeSaber(saberId);
		
		if(!success) {
			throw new DomainException("There's no saber with this id");
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
