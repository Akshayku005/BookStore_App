package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {
    @Query(value = "select * from user_registration where email_id = :email_id", nativeQuery = true)
    List<UserRegistration> findUserByEmailId(String email_id);
}
