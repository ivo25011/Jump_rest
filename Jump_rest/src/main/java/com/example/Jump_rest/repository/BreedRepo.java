package com.example.Jump_rest.repository;

import com.example.Jump_rest.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Breed entity.
 */
@Repository
public interface BreedRepo extends JpaRepository<Breed, Long> {
}
