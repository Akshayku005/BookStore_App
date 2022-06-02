package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);
    // String getToken(String email);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String emailId);

    UserRegistration updateUser(String email, UserDTO userDTO);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);
}
