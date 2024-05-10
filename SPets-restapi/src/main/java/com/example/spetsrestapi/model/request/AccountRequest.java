package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AccountRequest implements Serializable {

    @NotEmpty(message = "Fullname is required!")
    protected String name;

    @NotEmpty(message = "Username is required!")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must not contain special characters or spaces!")
    protected String username;

    @NotEmpty(message = "Password is required!")
    protected String password;

}
