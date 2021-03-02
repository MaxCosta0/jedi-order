package com.maxley.jediorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.maxley.jediorder.domain.Jedi;
import com.maxley.jediorder.domain.Saber;
import com.maxley.jediorder.repository.SaberRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SaberServiceTest {

	@InjectMocks
	private SaberService saberService;
	
	@Mock
	private SaberRepository saberRepository;
	
	@Mock
	private JediService jediService;
	
	@BeforeEach
	public void setup() {
		Jedi jedi = new Jedi(1L, "Maxley", 27.800);
		Saber saber = new Saber(1L, "green", jedi);
		
		List<Saber> sabers = new ArrayList<>();
		sabers.add(saber);
		
		Mockito.when(saberRepository.save(Mockito.any(Saber.class))).thenReturn(saber);
		Mockito.when(jediService.findJedi(jedi.getName())).thenReturn(Optional.of(jedi));
		Mockito.when(saberRepository.findByJedi(Mockito.any(Jedi.class))).thenReturn(sabers);
	}
	
	@Test
	public void whenSaveSaber_thenReturnSaber() {
		Jedi jedi = new Jedi(1L, "Maxley", 27.800);
		Saber expectedSaber = new Saber(1L, "green", jedi);
		Saber actualSaber = saberService.saveSaber(expectedSaber);
		
		Assertions.assertEquals(expectedSaber, actualSaber);
	}
	
	@Test
	public void whenFindAllSaberFromJedi_thenReturnSaber() {
		Jedi jedi = new Jedi(1L, "Maxley", 27.800);
		Saber saber = new Saber(1L, "green", jedi);
		List<Saber> expectedSaber = new ArrayList<>();
		expectedSaber.add(saber);
		
		List<Saber> actualSaber = saberService.findAllSaberFromJedi(jedi.getName());
		
		Assertions.assertEquals(expectedSaber, actualSaber);
	}
}
