package com.bridgelabz.bookstoreapp.service;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.Book;


import java.util.List;

public interface IBookService {
    String createBook(BookDTO bookDTO);
    List<Book> getAllBookData(String token);
    Book getBookDataById(String token);
    List<Book> getBookByName(String bookName);
    List<Book> sortedListOfBooksInAscendingOrder();
    List<Book> sortedListOfBooksInDescendingOrder();
    String deleteRecordById(String token);
    Book updateRecordById(String token,BookDTO bookDTO);
    List<Book> getBookByAuthorName(String authorName);

    Book updataBooksByQuantity(String token, int quantity);





}
