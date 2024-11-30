package com.example.Jump_rest.repository;

import com.example.Jump_rest.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Animal entity.
 */
@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

    Optional<Animal> findByName(String name);

}
