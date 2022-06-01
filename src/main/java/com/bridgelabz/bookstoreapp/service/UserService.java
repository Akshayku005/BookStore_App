package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreCustomException;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {
    @Autowired
    private UserRegistrationRepository userRepository;

    @Override
    public UserRegistration createUser(UserDTO userDTO) {
        UserRegistration userRegistrationData = null;
        userRegistrationData = new UserRegistration(userDTO);
        log.debug("BooktStoreData: " + userRegistrationData.toString());
        return userRepository.save(userRegistrationData);
    }

    @Override
    public List<UserRegistration> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserRegistration getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new BookStoreCustomException("BookStore user with this id " + id + " doest not exists!"));
    }

    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        return null;
    }

    @Override
    public List<UserRegistration> getByEmailId(String email_id) {
        return userRepository.findUserByEmailId(email_id);
    }

    @Override
    public UserRegistration updateUserBookStoreData(int id, UserDTO userDTO) {
        UserRegistration userRegistration = this.getById(id);
        userRegistration.updateBookStoreData(userDTO);
        return userRepository.save(userRegistration);
    }
}
