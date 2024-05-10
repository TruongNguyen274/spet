package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.HealthRecord;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.request.HealthRecordSaveRequest;
import com.example.spetsrestapi.model.response.HealthRecordResponse;
import com.example.spetsrestapi.repository.HealthRecordRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.service.HealthRecordService;
import com.example.spetsrestapi.service.UploadFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<HealthRecordResponse> getAll() {
        try {
            List<HealthRecordResponse> recordResponseList = new ArrayList<>();
            List<HealthRecord> healthRecordList = healthRecordRepository.findAll();
            for (HealthRecord healthRecord : healthRecordList) {
                recordResponseList.add(modelMapper.map(healthRecord, HealthRecordResponse.class));
            }

            return recordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<HealthRecordResponse> getByPet(Long petId) {
        try {
            List<HealthRecordResponse> recordResponseList = new ArrayList<>();
            List<HealthRecord> healthRecordList = healthRecordRepository.findByPet(petRepository.findById(petId).orElse(null));
            for (HealthRecord healthRecord : healthRecordList) {
                recordResponseList.add(modelMapper.map(healthRecord, HealthRecordResponse.class));
            }

            return recordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Transactional
    @Override
    public HealthRecordResponse save(HealthRecordSaveRequest request) {
        try {
            HealthRecord healthRecord = modelMapper.map(request, HealthRecord.class);
            Pet pet = petRepository.findById(request.getPetId()).orElse(null);
            if (!ObjectUtils.isEmpty(pet)) {
                pet.setHealth(request.getHealth());
                pet.setHeight(request.getHeight());
                pet.setWeight(request.getWeight());
                petRepository.save(pet);
                healthRecord.setPet(pet);
            }
            healthRecordRepository.save(healthRecord);

            return modelMapper.map(healthRecord, HealthRecordResponse.class);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }
}
