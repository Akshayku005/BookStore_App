package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.Util.EmailSenderService;
import com.bridgelabz.bookstoreapp.Util.TokenUtility;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    @Override
    public String addUser(UserDTO userDTO) {
        UserRegistration newUser= new UserRegistration(userDTO);
        userRepository.save(newUser);
        String token = util.createToken(newUser.getUserId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newUser.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8098/user/getBy/"+token);
        return token;
    }

    @Override
    public List<UserRegistration> getAllUsers() {
        List<UserRegistration> getUsers= userRepository.findAll();
        return getUsers;
    }
    @Override
    public Object getUserByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> getUser= userRepository.findById(id);
        if(getUser.isPresent()){
            mailService.sendEmail("priya.sports123@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +getUser.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8098/user/getBy/"+token);
            return getUser;

        }
        else {
            throw new BookStoreException("Record for provided userId is not found");
        }
    }

    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Optional<UserRegistration> login = userRepository.findByEmailid(userLoginDTO.getEmail());
        if(login.isPresent()){
            String pass = login.get().getPassword();
            if(login.get().getPassword().equals(userLoginDTO.getPassword())){
                dto.setMessage("login successful ");
                dto.setData(login.get());
                return dto;
            }

            else {
                dto.setMessage("Sorry! login is unsuccessful");
                dto.setData("Wrong password");
                return dto;
            }
        }
        return new ResponseDTO("User not found!","Wrong email");
    }

    @Override
    public String forgotPassword(String email, String password) {
        Optional<UserRegistration> isUserPresent = userRepository.findByEmailid(email);

        if(!isUserPresent.isPresent()) {
            throw new BookStoreException("Book record does not found");
        }
        else {
            UserRegistration user = isUserPresent.get();
            user.setPassword(password);
            userRepository.save(user);
            return "Password updated successfully";
        }

    }

    @Override
    public Object getUserByEmailId(String emailId) {

        return userRepository.findByEmailid(emailId);
    }

    @Override
    public UserRegistration updateUser(String id, UserDTO userDTO) {
        Optional<UserRegistration> updateUser = userRepository.findByEmailid(id);
        if(updateUser.isPresent()) {
            UserRegistration newBook = new UserRegistration(updateUser.get().getUserId(),userDTO);
            userRepository.save(newBook);
            String token = util.createToken(newBook.getUserId());
            mailService.sendEmail(newBook.getEmail(),"Welcome "+newBook.getFirstName(),"Click here \n http://localhost:8098/user/getBy/"+token);
            return newBook;

        }
        throw new BookStoreException("Book Details for email not found");
    }
    @Override
    public List<UserRegistration> getAllUserDataByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> isContactPresent=userRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserRegistration> listOfUsers=userRepository.findAll();
            mailService.sendEmail("priya.sports123@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8098/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }

    @Override
    public UserRegistration updateRecordById(Integer id, UserDTO userDTO) {
//        Integer id= util.decodeToken(token);
        Optional<UserRegistration> addressBook = userRepository.findById(id);
        if(addressBook.isPresent()) {
            UserRegistration newBook = new UserRegistration(id,userDTO);
            userRepository.save(newBook);

            return newBook;

        }
        throw new BookStoreException("User Details for id not found");
    }
}
