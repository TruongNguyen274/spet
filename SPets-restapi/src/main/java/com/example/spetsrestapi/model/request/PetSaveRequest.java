package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Setter
@Getter
public class PetSaveRequest implements Serializable {

    private long id;

    @NotNull(message = "OwnerId is required!")
    private long ownerId;

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Species is required!")
    private String species;

    @NotBlank(message = "Color is required!")
    private String color;

    @NotBlank(message = "BirthDay is required!")
    private String birthDay;

    private double weight;

    private double height;

    @NotBlank(message = "Health is required!")
    private String health;

    private boolean status;

}
