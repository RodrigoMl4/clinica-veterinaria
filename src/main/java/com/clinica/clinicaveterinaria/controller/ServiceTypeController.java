package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.model.ServiceType;
import com.clinica.clinicaveterinaria.service.ServiceTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service-types")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceTypeController {

  @Autowired
  ServiceTypeService serviceTypeService;

  @GetMapping
  public List<ServiceType> getAllServiceTypes() {
    return serviceTypeService.findAll();
  }

  @PostMapping
  public ServiceType createServiceType(ServiceType serviceType) {
    return serviceTypeService.save(serviceType);
  }

  @GetMapping("{id}")
  public ServiceType getServiceTypeById(Integer serviceTypeId) {
    return serviceTypeService.findById(serviceTypeId);
  }

  @PutMapping
  public ServiceType updateServiceType(Integer serviceTypeId, ServiceType serviceTypeDetails) {
    return serviceTypeService.update(serviceTypeId, serviceTypeDetails);
  }

  @DeleteMapping
  public void deleteServiceType(Integer serviceTypeId) {
    serviceTypeService.deleteById(serviceTypeId);
  }

}
