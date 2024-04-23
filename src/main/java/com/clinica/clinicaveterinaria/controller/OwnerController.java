package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.model.Owner;
import com.clinica.clinicaveterinaria.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable(value = "id") Integer ownerId) {
        Owner owner = ownerService.findById(ownerId);
        if(owner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(owner);
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        owner.setClientCode("DGC-" + owner.getCi());
        return ownerService.save(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable(value = "id") Integer ownerId,
                                             @RequestBody Owner ownerDetails) {
        Owner updatedOwner = ownerService.update(ownerId, ownerDetails);
        if(updatedOwner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedOwner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable(value = "id") Integer ownerId) {
        Owner owner = ownerService.findById(ownerId);
        if(owner == null) {
            return ResponseEntity.notFound().build();
        }

        ownerService.deleteById(ownerId);
        return ResponseEntity.ok().build();
    }
}