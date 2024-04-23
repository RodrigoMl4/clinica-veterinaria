package com.clinica.clinicaveterinaria.service;

import com.clinica.clinicaveterinaria.model.Owner;
import java.util.List;

public interface OwnerService {
    List<Owner> findAll();
    Owner findById(Integer id);
    Owner save(Owner owner);
    Owner update(Integer id, Owner owner);
    void deleteById(Integer id);
}