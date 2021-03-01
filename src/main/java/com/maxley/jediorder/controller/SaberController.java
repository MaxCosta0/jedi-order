package com.maxley.jediorder.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maxley.jediorder.domain.Saber;
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
		return saberService.findAllSaberFromJedi(jediName);	
	}
	
}
