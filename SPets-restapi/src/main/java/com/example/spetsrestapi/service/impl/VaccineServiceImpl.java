package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Vaccination;
import com.example.spetsrestapi.model.request.VaccineRequest;
import com.example.spetsrestapi.model.response.PetResponse;
import com.example.spetsrestapi.model.response.VaccineOwnerResponse;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.repository.VaccineRepository;
import com.example.spetsrestapi.service.UploadFileService;
import com.example.spetsrestapi.service.VaccineService;
import com.example.spetsrestapi.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VaccineOwnerResponse> getByOwner(long ownerId) {
        try {
            List<VaccineOwnerResponse> response = new ArrayList<>();
            Account accountOwner = accountRepository.findById(ownerId).orElse(null);
            if (!ObjectUtils.isEmpty(accountOwner)) {
                List<Pet> petList = petRepository.findByOwnerAndStatusIsTrue(accountOwner);
                for (Pet pet : petList) {
                    List<Vaccination> vaccinationList = vaccineRepository.findByPet(pet);
                    if (!vaccinationList.isEmpty()) {
                        VaccineOwnerResponse vaccineOwnerResponse = new VaccineOwnerResponse();
                        PetResponse petResponse = modelMapper.map(pet, PetResponse.class);
                        vaccineOwnerResponse.setPet(petResponse);

                        for (Vaccination vaccination : vaccinationList) {
                            vaccineOwnerResponse = modelMapper.map(vaccination, VaccineOwnerResponse.class);
                        }
                        response.add(vaccineOwnerResponse);
                    }
                }
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<VaccineOwnerResponse> getByPet(long petId) {
        try {
            Pet pet = new Pet();
            pet.setId(petId);

            List<VaccineOwnerResponse> response = new ArrayList<>();

            List<Vaccination> vaccinationList = vaccineRepository.findByPet(pet);
            if (!vaccinationList.isEmpty()) {
                VaccineOwnerResponse vaccineOwnerResponse = new VaccineOwnerResponse();
                PetResponse petResponse = modelMapper.map(pet, PetResponse.class);
                vaccineOwnerResponse.setPet(petResponse);

                for (Vaccination vaccination : vaccinationList) {
                    vaccineOwnerResponse = modelMapper.map(vaccination, VaccineOwnerResponse.class);
                    response.add(vaccineOwnerResponse);
                }
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public VaccineOwnerResponse save(VaccineRequest request) {
        try {
            Optional<Pet> petOptional = petRepository.findById(request.getPetId());
            if (!petOptional.isPresent()) {
                throw new ApplicationException();
            }

            Vaccination vaccination = new Vaccination();
            vaccination.setPet(petOptional.get());
            vaccination.setName(request.getName());
            vaccination.setDate(DateUtil.convertStringToDate(request.getDate(), "dd/MM/yyyy"));
            vaccination.setStatus("PENDING");

            vaccineRepository.save(vaccination);
            return new VaccineOwnerResponse();
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}
