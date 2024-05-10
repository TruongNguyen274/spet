package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByStatus(String status);

    List<Booking> findAll();

}
