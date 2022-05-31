package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


public @Data class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Name")
    public String firstName;
    @Pattern(regexp = "[A-Za-z\\s]+", message = "Invalid Last Name")
    public String lastName;
    @Pattern(regexp = "([a-zA-Z0-9./.-])+.([a-zA-Z0-9./.-])?@([a-z]{2,7})+.([a-z]{2,4})+.([a-z]{2,4})?", message = "Valid format is: abc.xyz@gmail.com")
    public String email_id;
    @NotBlank(message = "Please enter your address !!")
    public String address;

    public UserDTO(String firstName, String lastName, String email_id, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email_id = email_id;
        this.address = address;
    }
}
