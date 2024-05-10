package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.dto.FileDTO;
import com.example.spetsrestapi.model.entity.Media;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.request.MediaSaveRequest;
import com.example.spetsrestapi.model.response.MediaResponse;
import com.example.spetsrestapi.repository.MediaRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.service.MediaService;
import com.example.spetsrestapi.service.UploadFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<MediaResponse> getAllByPetOrderByUpdatedAtDesc(long petId) {
        try {
            List<MediaResponse> mediaResponseList = new ArrayList<>();
            Pet pet = petRepository.findById(petId).orElse(null);
            List<Media> mediaList = mediaRepository.findAllByPetOrderByUpdatedAtDesc(pet);
            for (Media media : mediaList) {
                mediaResponseList.add(modelMapper.map(media, MediaResponse.class));
            }

            return mediaResponseList;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public MediaResponse save(MediaSaveRequest request) {
        try {
            Media media = modelMapper.map(request, Media.class);
            // uploadFile
            if (request.getPathMul() != null &&
                    !ObjectUtils.isEmpty(request.getPathMul().getOriginalFilename())) {
                FileDTO fileDTOBack = uploadFileService.uploadFile(request.getPathMul());
                media.setPath(fileDTOBack.getPath());
            }
            mediaRepository.save(media);

            return modelMapper.map(media, MediaResponse.class);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }
}
