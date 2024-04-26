package com.clinica.clinicaveterinaria.service;

import com.clinica.clinicaveterinaria.model.ServiceType;
import java.util.List;
public interface ServiceTypeService {
    List<ServiceType> findAll();
    ServiceType findById(Integer id);
    ServiceType save(ServiceType serviceType);

    ServiceType update(Integer id, ServiceType serviceType);
    void deleteById(Integer id);
}
