package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.HealthRecord;
import com.example.spetsrestapi.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

    List<HealthRecord> findByPet(Pet pet);

}
