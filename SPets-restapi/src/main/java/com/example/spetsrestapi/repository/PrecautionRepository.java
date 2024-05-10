package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Precaution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecautionRepository extends JpaRepository<Precaution, Long> {

    List<Precaution> findPrecautionByIdIn(List<Long> ids);


}
