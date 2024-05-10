package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByPet(Pet pet);

    List<Schedule> findByPet_IdIn(List<Long> petIds);

}
