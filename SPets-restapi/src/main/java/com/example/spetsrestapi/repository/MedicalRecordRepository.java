package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.entity.MedicalRecord;
import com.example.spetsrestapi.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findAllByPetOrderByUpdatedAtDesc(Pet pet);

    List<MedicalRecord> findAllByHospitalOrderByUpdatedAtDesc(Hospital hospital);
}
