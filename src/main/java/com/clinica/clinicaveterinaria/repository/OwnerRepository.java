package com.clinica.clinicaveterinaria.repository;


import com.clinica.clinicaveterinaria.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
