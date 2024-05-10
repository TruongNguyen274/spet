package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.entity.MedicalRecord;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.request.MedicalRecordSaveRequest;
import com.example.spetsrestapi.model.response.MedicalRecordResponse;
import com.example.spetsrestapi.repository.HospitalRepository;
import com.example.spetsrestapi.repository.MedicalRecordRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.service.MedicalRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MedicalRecordResponse> getAllByPetOrderByUpdatedAtDesc(long petId) {
        try {
            List<MedicalRecordResponse> medicalRecordResponseList = new ArrayList<>();
            Pet pet = petRepository.findById(petId).orElse(null);
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAllByPetOrderByUpdatedAtDesc(pet);
            for (MedicalRecord medicalRecord : medicalRecordList) {
                medicalRecordResponseList.add(modelMapper.map(medicalRecord, MedicalRecordResponse.class));
            }

            return medicalRecordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<MedicalRecordResponse> getAllByHospitalOrderByUpdatedAtDesc(long hospitalId) {
        try {
            List<MedicalRecordResponse> medicalRecordResponseList = new ArrayList<>();
            Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAllByHospitalOrderByUpdatedAtDesc(hospital);
            for (MedicalRecord medicalRecord : medicalRecordList) {
                medicalRecordResponseList.add(modelMapper.map(medicalRecord, MedicalRecordResponse.class));
            }

            return medicalRecordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public MedicalRecordResponse save(MedicalRecordSaveRequest request) {
        try {
            MedicalRecord medicalRecord = modelMapper.map(request, MedicalRecord.class);

            Pet pet = petRepository.findById(request.getPetId()).orElse(null);
            medicalRecord.setPet(pet);
            Hospital hospital = hospitalRepository.findById(request.getHospitalId()).orElse(null);
            medicalRecord.setHospital(hospital);

            medicalRecordRepository.save(medicalRecord);

            return modelMapper.map(medicalRecord, MedicalRecordResponse.class);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }
}
