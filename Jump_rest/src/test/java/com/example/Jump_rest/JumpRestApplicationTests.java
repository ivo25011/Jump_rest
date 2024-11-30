package com.example.Jump_rest;

import com.example.Jump_rest.controller.AnimalController;
import com.example.Jump_rest.dto.AnimalDto;
import com.example.Jump_rest.exceptions.ResourceNotFoundException;
import com.example.Jump_rest.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class JumpRestApplicationTests {

	private MockMvc mock;

	@Mock
	private AnimalService animalService;

	@InjectMocks
	private AnimalController animalController;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp(){
		objectMapper = new ObjectMapper();
		mock = MockMvcBuilders.standaloneSetup(animalController).build();
	}

	@Test
	void testGetAnimals() throws Exception{
		AnimalDto animalDto = new AnimalDto();
		animalDto.setName("Charlie");
		animalDto.setAge(3);
		animalDto.setBreedId(1L);
		animalDto.setGender("MALE");

		when(animalService.getAnimals()).thenReturn(List.of(animalDto));

		ResultActions response = mock.perform(get("/"));
		response.andExpect(jsonPath("$[0].name").value(animalDto.getName()))
				.andExpect(jsonPath("$[0].age").value(animalDto.getAge()));

		verify(animalService, times(1)).getAnimals();
	}

	@Test
	void testAddAnimal() throws Exception {
		AnimalDto animalDto = new AnimalDto();
		animalDto.setName("Max");
		animalDto.setAge(2);
		animalDto.setBreedId(1L);
		animalDto.setGender("MALE");

		when(animalService.addAnimal(any(AnimalDto.class))).thenReturn(animalDto);

		ResultActions response = mock.perform(post("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(animalDto)));
		response.andExpect(jsonPath("$.name").value(animalDto.getName()))
				.andExpect(jsonPath("$.age").value(animalDto.getAge()));

		verify(animalService, times(1)).addAnimal(any(AnimalDto.class));
	}

	@Test
	void testRemoveAnimal() throws Exception {
		long animalId = 1L;

		ResultActions response = mock.perform(delete("/{id}", animalId));
		response.andExpect(status().isOk());

		verify(animalService, times(1)).removeAnimal(animalId);
	}
}
