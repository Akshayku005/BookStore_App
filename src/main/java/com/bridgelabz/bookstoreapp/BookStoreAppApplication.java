package com.bridgelabz.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@Slf4j
public class BookStoreAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
        System.out.println("Welcome to  Book Store !!");
    }
}
