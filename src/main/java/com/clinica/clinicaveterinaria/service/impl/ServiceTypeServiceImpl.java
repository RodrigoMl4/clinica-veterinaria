package com.clinica.clinicaveterinaria.service.impl;

import com.clinica.clinicaveterinaria.model.ServiceType;
import com.clinica.clinicaveterinaria.repository.ServiceTypeRepository;
import com.clinica.clinicaveterinaria.service.ServiceTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

  ServiceTypeRepository serviceTypeRepository;

  @Autowired
  public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
    this.serviceTypeRepository = serviceTypeRepository;
  }

  @Override
  @Transactional
  public List<ServiceType> findAll() {
    return serviceTypeRepository.findAll();
  }

  @Override
  @Transactional
  public ServiceType findById(Integer id) {
    return serviceTypeRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("ServiceType not found for this id :: " + id));
  }

  @Override
  @Transactional
  public ServiceType save(ServiceType serviceType) {
    return serviceTypeRepository.save(serviceType);
  }

  @Override
  @Transactional
  public ServiceType update(Integer id, ServiceType serviceTypeDetails) {
    ServiceType serviceType = serviceTypeRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("ServiceType not found for this id :: " + id));

    serviceType.setServiceName(serviceTypeDetails.getServiceName());

    return serviceTypeRepository.save(serviceType);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    serviceTypeRepository.deleteById(id);
  }

}
