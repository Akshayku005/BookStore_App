package com.bridgelabz.bookstoreapp.service;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.Book;


import java.util.List;

public interface IBookService {
    Book insertBook (BookDTO bookDTO);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    void deleteBookById(int bookId);
    public List<Book> getBookByName(String bookName);
    Book updateBookById(int bookId, BookDTO bookDTO);
    public List<Book> sortingBookInAsce();
    public List<Book> sortingBookInDesc();
    public List<Book> getBookByAuthorName(String authorName);
}
