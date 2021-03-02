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
import com.maxley.jediorder.repository.JediRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JediServiceTest {
	
	@InjectMocks
	private JediService jediService;
	
	@Mock
	private JediRepository jediRepository;

	@BeforeEach
	public void setup() {
		Jedi jedi = new Jedi(1L, "Maxley", 27.800);
		Jedi jedi1 = new Jedi(2L, "Max", 27.900);
		
		List<Jedi> jediList = new ArrayList<>();
		jediList.add(jedi);
		jediList.add(jedi1);
		
		Mockito.when(jediRepository.save(jedi)).thenReturn(jedi);
		Mockito.when(jediRepository.findByName(jedi.getName())).thenReturn(Optional.of(jedi));
		Mockito.when(jediRepository.findAll()).thenReturn(jediList);
	}
	
	@Test
	public void whenSaveJedi_thenReturnJedi() {
		Jedi expectedJedi = new Jedi(1L, "Maxley", 27.800);
		Jedi actualJedi = jediService.saveJedi(expectedJedi);
		
		Assertions.assertEquals(expectedJedi, actualJedi);
	}
	
	@Test
	public void whenValidName_thenReturnJedi() {
		Jedi expectedJedi = new Jedi(1L, "Maxley", 27.800);
		String validName = "Maxley";
		Optional<Jedi> actualJedi = jediService.findJedi(validName);
		
		Assertions.assertEquals(Optional.of(expectedJedi), actualJedi);
		
	}
	
	@Test
	public void whenFindAll_thenReturnAllJedi() {
		Jedi jedi = new Jedi(1L, "Maxley", 27.800);
		Jedi jedi1 = new Jedi(2L, "Max", 27.900);
		List<Jedi> expectedList = new ArrayList<>();
		expectedList.add(jedi);
		expectedList.add(jedi1);
		
		List<Jedi> actualList = jediService.findAllJedi();
		
		Assertions.assertEquals(expectedList, actualList);
	}
}
