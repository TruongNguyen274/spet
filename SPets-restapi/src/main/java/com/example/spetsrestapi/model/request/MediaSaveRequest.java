package com.example.spetsrestapi.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

@Setter
@Getter
public class MediaSaveRequest implements Serializable {

    private Long id;

    private Long petId;

    private MultipartFile pathMul;

    private boolean status;

}
