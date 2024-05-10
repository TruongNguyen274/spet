package com.example.spetsrestapi.repository;

import com.example.spetsrestapi.model.entity.Media;
import com.example.spetsrestapi.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    /**
     *
     * @param pet
     * @return List<Pet>
     */
    List<Media> findAllByPetOrderByUpdatedAtDesc(Pet pet);

}
