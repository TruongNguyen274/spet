package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.request.MediaSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.MediaResponse;
import com.example.spetsrestapi.service.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mePetService;

    @GetMapping(value = "/pet")
    public ResponseEntity<ApiResponse<List<MediaResponse>>> getAllByPet(@RequestParam long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(mePetService.getAllByPetOrderByUpdatedAtDesc(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save/photo/{petId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<MediaResponse>> save(@PathVariable long petId,
             @RequestPart(name = "avatarMul") MultipartFile avatarMul){
        ApiResponse apiResponse = new ApiResponse();

        MediaSaveRequest request = new MediaSaveRequest();
        request.setPetId(petId);
        request.setPathMul(avatarMul);
        request.setStatus(true);

        apiResponse.ok(mePetService.save(request));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}