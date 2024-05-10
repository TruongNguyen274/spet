package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.request.HospitalSaveRequest;
import com.example.spetsrestapi.model.response.HospitalResponse;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.HospitalRepository;
import com.example.spetsrestapi.service.HospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HospitalResponse> getHospital() {
        try {
            List<HospitalResponse> response = new ArrayList<>();
            List<Hospital> hospitalList = hospitalRepository.findAllByStatusIsTrue();
            for (Hospital hospital : hospitalList) {
                HospitalResponse hospitalResponse = modelMapper.map(hospital, HospitalResponse.class);
                response.add(hospitalResponse);
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public HospitalResponse getHospitalByAccountId(long accountId) {
        try {
            Hospital hospital = hospitalRepository.findByStatusIsTrueAndAccountId(accountId);
            HospitalResponse hospitalResponse = modelMapper.map(hospital, HospitalResponse.class);

            return hospitalResponse;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public HospitalResponse save(HospitalSaveRequest request) {
        try {
            Hospital hospital = hospitalRepository.findByAccountId(request.getAccountId());
            hospital = modelMapper.map(request, Hospital.class);
            hospitalRepository.save(hospital);
            HospitalResponse hospitalResponse = modelMapper.map(hospital, HospitalResponse.class);

            return hospitalResponse;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public Hospital findById(long hospitalId) {
        try {
            Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
            return hospitalOptional.orElse(null);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

}
