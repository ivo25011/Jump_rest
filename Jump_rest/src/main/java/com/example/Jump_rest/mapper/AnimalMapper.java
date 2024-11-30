package com.example.Jump_rest.mapper;

import com.example.Jump_rest.dto.AnimalDto;
import com.example.Jump_rest.dto.AnimalWithDetailsDto;
import com.example.Jump_rest.entity.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper class for convert entities to DTO or vice versa.
 */
@Component
public class AnimalMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalDto mapAnimalToDto(Animal animal) {
        return modelMapper.map(animal, AnimalDto.class);
    }

    public Animal mapAnimalToEntity(AnimalDto animalDto) {
        return modelMapper.map(animalDto, Animal.class);
    }

    public AnimalWithDetailsDto mapAnimalWithDetailsToDto(Animal animal) {
        return modelMapper.map(animal, AnimalWithDetailsDto.class);
    }
}
