package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.VaccineRequest;
import com.example.spetsrestapi.model.response.VaccineOwnerResponse;

import java.util.List;

public interface VaccineService {

    List<VaccineOwnerResponse> getByOwner(long ownerId);

    List<VaccineOwnerResponse>  getByPet(long petId);

    VaccineOwnerResponse save(VaccineRequest request);

}
