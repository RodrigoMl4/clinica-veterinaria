package com.clinica.clinicaveterinaria.repository;

import com.clinica.clinicaveterinaria.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
