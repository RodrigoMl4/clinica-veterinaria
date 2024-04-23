package com.clinica.clinicaveterinaria.service.impl;

import com.clinica.clinicaveterinaria.model.Owner;
import com.clinica.clinicaveterinaria.repository.OwnerRepository;
import com.clinica.clinicaveterinaria.service.OwnerService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = ownerRepository.findAll();
        owners.forEach(owner -> Hibernate.initialize(owner.getPets()));
        return owners;
    }

    @Override
    public Owner findById(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found for this id :: " + id));
        Hibernate.initialize(owner.getPets());
        return owner;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Integer id, Owner ownerDetails) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found for this id :: " + id));

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