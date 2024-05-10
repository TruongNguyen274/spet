package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.BookingSaveRequest;
import com.example.spetsrestapi.model.response.BookingResponse;

import java.util.List;

public interface BookingService {

    List<BookingResponse> getAllByStatus(String status);

    List<BookingResponse> getAll();

    BookingResponse save(BookingSaveRequest request);

    BookingResponse update(BookingSaveRequest request);

}
