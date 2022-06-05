package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book_store")
public @Data class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_Id")
    private Integer bookId;

    private String bookName;

    private String authorName;

    private String bookDescription;

    private String bookImg;

    private Integer price;

    private Integer quantity;

    public Book() {

    }

    public Book(BookDTO bookDTO) {
        this.updateBook(bookDTO);
    }

    public Book(int id, BookDTO bookDTO) {
        this.bookId=id;
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }


    public void updateBook(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
}
