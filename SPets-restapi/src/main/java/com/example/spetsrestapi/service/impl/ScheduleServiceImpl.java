package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Schedule;
import com.example.spetsrestapi.model.request.ScheduleSaveRequest;
import com.example.spetsrestapi.model.response.PetResponse;
import com.example.spetsrestapi.model.response.ScheduleResponse;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.repository.ScheduleRepository;
import com.example.spetsrestapi.service.ScheduleService;
import com.example.spetsrestapi.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ScheduleResponse> getAll() {
        try {
            List<ScheduleResponse> scheduleResponseList = new ArrayList<>();
            List<Schedule> scheduleList = scheduleRepository.findAll();
            for (Schedule schedule : scheduleList) {
                scheduleResponseList.add(modelMapper.map(schedule, ScheduleResponse.class));
            }

            return scheduleResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public ScheduleResponse getById(Long id) {
        try {
            ScheduleResponse response = new ScheduleResponse();
            Schedule schedule = scheduleRepository.findById(id).orElse(null);
            if (!ObjectUtils.isEmpty(schedule)) {
                response = modelMapper.map(schedule, ScheduleResponse.class);
            }
            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<ScheduleResponse> getByPet(Long petId) {
        try {
            List<ScheduleResponse> recordResponseList = new ArrayList<>();
            List<Schedule> scheduleList = scheduleRepository.findByPet(petRepository.findById(petId).orElse(null));
            for (Schedule schedule : scheduleList) {
                recordResponseList.add(modelMapper.map(schedule, ScheduleResponse.class));
            }

            return recordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Transactional
    @Override
    public ScheduleResponse save(ScheduleSaveRequest request) {
        try {
            ScheduleResponse response = new ScheduleResponse();
            Schedule schedule = scheduleRepository.findById(request.getId()).orElse(null);
            if (schedule == null) {
                schedule = new Schedule();
            }

            Pet pet = petRepository.findById(request.getPetId()).orElse(null);
            schedule.setPet(pet);

            schedule.setTitle(request.getTitle());
            schedule.setDescription(request.getDescription());
            schedule.setActivityDate(request.getActivityDate());
            schedule.setActivityType(request.getActivityType());
            schedule.setStartTime(request.getStartTime());
            schedule.setRepeatInterval(request.getRepeatInterval());

            scheduleRepository.saveAndFlush(schedule);

            response = modelMapper.map(schedule, ScheduleResponse.class);
            response.setPet(modelMapper.map(pet, PetResponse.class));

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<ScheduleResponse> getByAccount(Long accountId) {
        try {
            Account account = new Account();
            account.setId(accountId);

            List<Pet> petList = petRepository.findByOwnerAndStatusIsTrue(account);

            if (petList == null) {
                return null;
            }

            List<Long> idList = new ArrayList<>(); // Danh sách để lưu trữ ID

            for (Pet pet : petList) {
                idList.add(pet.getId()); // Trích xuất ID từ mỗi đối tượng Pet và thêm vào danh sách ID
            }

            List<ScheduleResponse> recordResponseList = new ArrayList<>();
            List<Schedule> scheduleList = scheduleRepository.findByPet_IdIn(idList);

            for (Schedule schedule : scheduleList) {
                recordResponseList.add(modelMapper.map(schedule, ScheduleResponse.class));
            }

            return recordResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public ScheduleResponse delete(long id) {
        try {
            ScheduleResponse response = new ScheduleResponse();
            scheduleRepository.deleteById(id);
            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}
