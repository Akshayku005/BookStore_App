package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistration;

import java.util.List;

public interface IUserService {
    UserRegistration createUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    UserRegistration getById(int id);

    List<UserRegistration> getByEmailId(String email_id);

    UserRegistration updateUserBookStoreData(int id, UserDTO userDTO);
}
