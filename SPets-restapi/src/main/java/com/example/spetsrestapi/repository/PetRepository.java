package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByStatusIsTrue();

    List<Pet> findByOwnerAndStatusIsTrue(Account owner);

}
