package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.clinica.clinicaveterinaria.model.Pet;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable(value = "id") Integer petId) {
        Pet pet = petService.findById(petId);
        if(pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pet);
    }

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.save(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") Integer petId,
                                         @RequestBody Pet petDetails) {
        Pet updatedPet = petService.update(petId, petDetails);
        if(updatedPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable(value = "id") Integer petId) {
        Pet pet = petService.findById(petId);
        if(pet == null) {
            return ResponseEntity.notFound().build();
        }

        petService.deleteById(petId);
        return ResponseEntity.ok().build();
    }
}
