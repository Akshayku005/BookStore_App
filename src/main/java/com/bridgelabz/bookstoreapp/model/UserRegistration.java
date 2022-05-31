package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_registration")
public @Data class  UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_store_id")
    private Integer userId;

    private String firstName;
    private String lastName;
    private String email_id;
    private String address;


    public UserRegistration() {

    }
    public UserRegistration(UserDTO userDTO) {
        this.updateBookStoreData(userDTO);
    }
    public void updateBookStoreData( UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();;
        this.lastName = userDTO.getLastName();
        this.email_id = userDTO.getEmail_id();
        this.address = userDTO.getAddress();

    }

}
