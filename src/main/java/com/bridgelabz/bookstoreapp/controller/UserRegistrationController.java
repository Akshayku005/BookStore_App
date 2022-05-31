package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class UserRegistrationController {
    @Autowired
    IUserService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody UserDTO userDTO) {
        UserRegistration userRegistration = userRegistrationService.createUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("created BookStore user data succesfully", userRegistration);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllUser() {
        List<UserRegistration> userRegistrations = userRegistrationService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Get call Success", userRegistrations);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getuserById(@PathVariable ("id") int id) {
        UserRegistration userRegistration = userRegistrationService.getById(id);
        ResponseDTO responseDTO = new ResponseDTO("GetById call Success", userRegistration);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyemail/{email_id}")
    public ResponseEntity<ResponseDTO> getUserByEmail_Id(@PathVariable("email_id") String email_id) {
        List<UserRegistration> userRegistration= null;
        userRegistration = userRegistrationService.getByEmailId(email_id);
        ResponseDTO responseDTO = new ResponseDTO("Get call search by email_id is successful!", userRegistration);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateByID(@PathVariable int id, @Valid @RequestBody UserDTO userDTO) {
        UserRegistration userRegistration = userRegistrationService.updateUserBookStoreData(id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("updated BookStore user data succesfully", userRegistration);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
