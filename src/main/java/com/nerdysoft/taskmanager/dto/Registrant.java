package com.nerdysoft.taskmanager.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Registrant {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 15)
    private String username;

    @Email()
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 15)
    private String password;
}
