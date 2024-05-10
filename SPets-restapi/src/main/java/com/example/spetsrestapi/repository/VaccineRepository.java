package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByPetAndStatusIsTrue(Pet pet);

    List<Vaccination> findAllByPetAndStatusIsFalse(Pet pet);

    List<Vaccination> findByPet(Pet pet);

}
