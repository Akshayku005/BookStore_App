package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


public @Data class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee  firstName is Not valid")
    public String firstName;

    @Pattern(regexp = "[A-Za-z\\s]+", message = " Lastname is invalid!")
    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "address can not be empty")
    private String address;

    @NotEmpty(message = "Password cant be empty")
    private String password;
}

