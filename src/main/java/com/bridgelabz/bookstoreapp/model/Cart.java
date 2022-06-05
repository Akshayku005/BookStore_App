package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Cart {
    @Id
    @GeneratedValue
    private Integer cartId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private Integer quantity;

    public Cart(Integer quantity, Book book, UserRegistration user) {
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public Cart() {
    }

    public Cart(int id, Integer quantity, Book book, UserRegistration userRegistration) {
        this.cartId=id;
        this.quantity = quantity;
        this.book = book;
        this.user = userRegistration;
    }
}