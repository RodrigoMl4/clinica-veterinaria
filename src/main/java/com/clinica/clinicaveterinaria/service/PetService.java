package com.clinica.clinicaveterinaria.service;

import com.clinica.clinicaveterinaria.model.Pet;
import java.util.List;

public interface PetService {
    List<Pet> findAll();
    Pet findById(Integer id);
    Pet save(Pet pet);
    Pet update(Integer id, Pet pet);
    void deleteById(Integer id);
}