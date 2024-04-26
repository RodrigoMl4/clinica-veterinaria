package com.clinica.clinicaveterinaria.service.impl;

import com.clinica.clinicaveterinaria.model.Pet;
import com.clinica.clinicaveterinaria.repository.PetRepository;
import com.clinica.clinicaveterinaria.service.PetService;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetServiceImpl implements PetService {

  private final PetRepository petRepository;

  @Autowired
  public PetServiceImpl(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Transactional
  @Override
  public List<Pet> findAll() {
    List<Pet> pets = petRepository.findAll();
    pets.forEach(pet -> Hibernate.initialize(pet.getOwner()));
    return pets;
  }

  @Transactional
  @Override
  public Pet findById(Integer id) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("Pet not found for this id :: " + id));
    Hibernate.initialize(pet.getOwner());
    return pet;
  }

  @Transactional
  @Override
  public Pet save(Pet pet) {
    return petRepository.save(pet);
  }

  @Transactional
  @Override
  public Pet update(Integer id, Pet petDetails) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("Pet not found for this id :: " + id));

    pet.setName(petDetails.getName());
    pet.setSpecies(petDetails.getSpecies());
    pet.setSex(petDetails.getSex());
    pet.setSterilized(petDetails.getSterilized());
    pet.setAge(petDetails.getAge());
    pet.setWeight(petDetails.getWeight());
    pet.setColor(petDetails.getColor());
    pet.setBreed(petDetails.getBreed());

    if (petDetails.getOwner() != null) {
      pet.setOwner(petDetails.getOwner());
    }

    return petRepository.save(pet);
  }

  @Transactional
  @Override
  public void deleteById(Integer id) {
    petRepository.deleteById(id);
  }
}