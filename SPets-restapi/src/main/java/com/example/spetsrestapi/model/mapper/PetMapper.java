package com.example.spetsrestapi.model.mapper;

import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.request.PetSaveRequest;
import com.example.spetsrestapi.model.response.PetResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    // Map Entity to Response
    PetResponse toResponse(Pet pet);

    List<PetResponse> toResponseList(List<Pet> petList);

    // Map Request to Entity
    Pet toEntity(PetSaveRequest request);
}
