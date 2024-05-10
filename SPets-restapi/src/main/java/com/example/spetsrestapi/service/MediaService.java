package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.MediaSaveRequest;
import com.example.spetsrestapi.model.response.MediaResponse;

import java.util.List;

public interface MediaService {

    List<MediaResponse> getAllByPetOrderByUpdatedAtDesc(long petId);

    MediaResponse save(MediaSaveRequest request);

}
