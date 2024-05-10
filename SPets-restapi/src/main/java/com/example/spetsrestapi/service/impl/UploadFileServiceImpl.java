package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.dto.FileDTO;
import com.example.spetsrestapi.service.UploadFileService;
import com.example.spetsrestapi.util.ConstantUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public FileDTO uploadFile(MultipartFile multipartFile) {
        try {
            if (multipartFile.getOriginalFilename() == null) {
                return null;
            }

            UUID uuid = UUID.randomUUID();
            String[] fileFrags = multipartFile.getOriginalFilename().split("\\.");
            String extension = fileFrags[fileFrags.length-1];
            String fileName;

            String uploadDir = ConstantUtil.PATH_FILE_UPLOAD;
            fileName = uuid + "." + extension;

            boolean success = saveFile(uploadDir, fileName, multipartFile);

            FileDTO fileDTO = null;
            if (success) {
                fileDTO = new FileDTO();
                fileDTO.setId(uuid.toString());
                fileDTO.setName(multipartFile.getOriginalFilename());
                fileDTO.setPath(fileName);
                fileDTO.setSize(multipartFile.getSize());
            }

            return fileDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                return true;
            } catch (IOException ioe) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

}
