package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Pet> getAll() {
        return petRepository.findAllByStatusIsTrue();
    }

    @Override
    public List<Pet> getByOwner(long ownerId) {
        Account owner = accountRepository.findById(ownerId).orElse(null);
        return petRepository.findByOwnerAndStatusIsTrue(owner);
    }

    @Override
    public Pet getById(long petId) {
        return petRepository.findById(petId).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }
}
