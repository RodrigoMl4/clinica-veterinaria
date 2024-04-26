package com.clinica.clinicaveterinaria.repository;

import com.clinica.clinicaveterinaria.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends
    JpaRepository<ServiceType, Integer> {
}
