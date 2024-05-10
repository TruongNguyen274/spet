package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.ScheduleSaveRequest;
import com.example.spetsrestapi.model.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {

    List<ScheduleResponse> getAll();

    ScheduleResponse getById(Long id);

    List<ScheduleResponse> getByPet(Long petId);

    ScheduleResponse save(ScheduleSaveRequest request);

    List<ScheduleResponse> getByAccount(Long accountId);

    ScheduleResponse delete(long id);

}
