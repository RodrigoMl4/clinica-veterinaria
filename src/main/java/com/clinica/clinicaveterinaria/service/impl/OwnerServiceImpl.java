package com.clinica.clinicaveterinaria.service.impl;

import com.clinica.clinicaveterinaria.model.Owner;
import com.clinica.clinicaveterinaria.repository.OwnerRepository;
import com.clinica.clinicaveterinaria.service.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

  private final OwnerRepository ownerRepository;

  @Autowired
  public OwnerServiceImpl(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  public List<Owner> findAll() {
    return ownerRepository.findAll();
  }

  @Override
  public Owner findById(Integer id) {
    return ownerRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("Owner not found for this id :: " + id));
  }

  @Override
  public Owner save(Owner owner) {
    return ownerRepository.save(owner);
  }

  @Override
  public Owner update(Integer id, Owner ownerDetails) {
    Owner owner = ownerRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("Owner not found for this id :: " + id));

    owner.setCi(ownerDetails.getCi());
    owner.setFirstName(ownerDetails.getFirstName());
    owner.setLastName(ownerDetails.getLastName());
    owner.setClientCode(ownerDetails.getClientCode());
    owner.setPhone(ownerDetails.getPhone());
    owner.setEmail(ownerDetails.getEmail());

    return ownerRepository.save(owner);
  }

  @Override
  public void deleteById(Integer id) {
    ownerRepository.deleteById(id);
  }
}