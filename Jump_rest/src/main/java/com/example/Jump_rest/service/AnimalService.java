package com.example.Jump_rest.service;

import com.example.Jump_rest.dto.AnimalDto;
import com.example.Jump_rest.dto.AnimalWithDetailsDto;
import com.example.Jump_rest.entity.Animal;
import com.example.Jump_rest.entity.Breed;
import com.example.Jump_rest.exceptions.ResourceNotFoundException;
import com.example.Jump_rest.mapper.AnimalMapper;
import com.example.Jump_rest.repository.AnimalRepo;
import com.example.Jump_rest.repository.BreedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for collect all business logic of project.
 */
@Service
public class AnimalService {

    @Autowired
    private AnimalRepo animalRepo;

    @Autowired
    private BreedRepo breedRepo;

    @Autowired
    private AnimalMapper animalMapper;

    /**
     * Get all animals.
     */
    public List<AnimalDto> getAnimals() {
        return animalRepo.findAll()
                .stream()
                .map(animal -> animalMapper.mapAnimalToDto(animal))
                .toList();
    }

    /**
     * Add the animal to DB.
     *
     * @param animalDto  the animal that to be added
     */
    public AnimalDto addAnimal(AnimalDto animalDto) {
        // Check if animal exists because It should not
        if(animalRepo.findByName(animalDto.getName()).isPresent()) {
            throw new IllegalArgumentException("Animal already added");
        }
        // Check if breed exists
        Breed breed = breedRepo.findById(animalDto.getBreedId())
                .orElseThrow(() -> new ResourceNotFoundException("Breed not found"));

        Animal animal = animalMapper.mapAnimalToEntity(animalDto);
        // Map breed on breedId
        animal.setBreed(breed);
        // Save to DB
        return animalMapper.mapAnimalToDto(animalRepo.save(animal));
    }


    /**
     * Remove the animal from DB.
     *
     * @param id  the id of animal
     */
    public void removeAnimal(Long id) {
        animalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));

        animalRepo.deleteById(id);
    }

    /**
     * Get the animal by id from DB.
     *
     * @param id  the id of animal
     */
    public AnimalDto getAnimal(Long id) {
        Animal animal = animalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found"));

        return animalMapper.mapAnimalToDto(animal);
    }

    /**
     * Update the animal at DB.
     *
     * @param animalDto  the animal that to be updated
     */
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        // Check if animal exists
        Optional<Animal> animal = animalRepo.findByName(animalDto.getName());
        if(!animal.isPresent()){
                throw new ResourceNotFoundException("Animal not found");
        }
        // Check if breed exists
        Breed breed = breedRepo.findById(animalDto.getBreedId())
                .orElseThrow(() -> new ResourceNotFoundException("Breed not found"));

        // Set all properties to new animal
        Animal existingAnimal = animal.get();
        existingAnimal.setName(animalDto.getName());
        existingAnimal.setAge(animalDto.getAge());
        if (animalDto.getGender() != null) {
            existingAnimal.setGender(Animal.Gender.valueOf(animalDto.getGender()));
        }
        else {
            throw new IllegalArgumentException("Gender has to be set");
        }
        existingAnimal.setBreed(breed);

        // Replace old animal with new one
        return animalMapper.mapAnimalToDto(animalRepo.save(existingAnimal));
    }

    /**
     * Get all animals with details.
     */
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return animalRepo.findAll()
                .stream()
                .map(animal -> animalMapper.mapAnimalWithDetailsToDto(animal)).toList();
    }
}
