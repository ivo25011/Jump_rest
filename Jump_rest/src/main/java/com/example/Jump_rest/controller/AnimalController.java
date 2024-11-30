package com.example.Jump_rest.controller;


import com.example.Jump_rest.dto.AnimalDto;
import com.example.Jump_rest.dto.AnimalWithDetailsDto;
import com.example.Jump_rest.entity.Animal;
import com.example.Jump_rest.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalService service;

    /*
    * metóda na načítanie všetkých zvieratiek
    */
    @GetMapping
    public List<AnimalDto> getAnimals() {
        return service.getAnimals();
    }

    /*
     * metóda na pridanie nového zvieratka
     */
    @PostMapping
    public AnimalDto addAnimal(@Valid @RequestBody AnimalDto animalDto) {
        return service.addAnimal(animalDto);
    }

    /*
     * metóda na zmazanie zvieratka
     */
    @DeleteMapping("/{id}")
    public void removeAnimal(@PathVariable Long id) {
        service.removeAnimal(id);
    }

    /*
     * metóda na načítanie zvieratka
     */
    @GetMapping("/{id}")
    public AnimalDto getAnimal(@PathVariable Long id) {
        return service.getAnimal(id);
    }

    /*
     * metóda na uloženie zmien zvieratka
     */
    @PutMapping
    public AnimalDto updateAnimal(@RequestBody AnimalDto animalDto) {
        return service.updateAnimal(animalDto);
    }

    /*
     * metóda na načítanie všetkých zvieratiek s detailnymi parametrami
     */
    @GetMapping("/details")
    public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
        return service.getAnimalsWithDetails();
    }
}
