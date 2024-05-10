package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {

    FileDTO uploadFile(MultipartFile multipartFile);

}
