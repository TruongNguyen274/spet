package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.*;
import com.example.spetsrestapi.model.request.BookingSaveRequest;
import com.example.spetsrestapi.model.response.BookingResponse;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.BookingRepository;
import com.example.spetsrestapi.repository.HospitalRepository;
import com.example.spetsrestapi.service.BookingService;
import com.example.spetsrestapi.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BookingResponse> getAll() {
        try {
            List<BookingResponse> bookingResponseList = new ArrayList<>();
            List<Booking> bookingList = bookingRepository.findAll();
            for (Booking booking : bookingList) {
                bookingResponseList.add(modelMapper.map(booking, BookingResponse.class));
            }

            return bookingResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<BookingResponse> getAllByStatus(String status) {
        try {
            List<BookingResponse> bookingResponseList = new ArrayList<>();
            List<Booking> bookingList = bookingRepository.findAllByStatus(status);

            for (Booking booking : bookingList) {
                bookingResponseList.add(modelMapper.map(booking, BookingResponse.class));
            }

            return bookingResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Transactional
    @Override
    public BookingResponse save(BookingSaveRequest request) {
        try {
            Booking bookingRecord = modelMapper.map(request, Booking.class);

            // account
            Account account = accountRepository.findById(request.getAccountId()).orElse(null);
            bookingRecord.setAccount(account);

            // hospital
            Hospital hospital = hospitalRepository.findById(request.getHospitalId()).orElse(null);
            bookingRecord.setHospital(hospital);
            bookingRepository.save(bookingRecord);

            BookingResponse response = modelMapper.map(bookingRecord, BookingResponse.class);

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public BookingResponse update(BookingSaveRequest request) {
        try {
            Booking bookingRecord = bookingRepository.findById(request.getId()).orElse(null);
            bookingRecord.setStatus(request.getStatus());
            bookingRepository.save(bookingRecord);

            BookingResponse response = modelMapper.map(bookingRecord, BookingResponse.class);

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}
