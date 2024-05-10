package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    List<Symptom> findByStatusIsTrue();

    List<Symptom> findSymptomByIdIn(List<Long> ids);

}

