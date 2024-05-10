package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;

import java.util.List;

public interface PetService {

    List<Pet> getAll();

    List<Pet> getByOwner(long ownerId);

    Pet getById(long petId);

    Pet save(Pet pet);

}
