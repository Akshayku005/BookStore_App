package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;
//Ability to insert BookDetails in DB
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.insertBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("created Book data successfully", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to get all book data by getAllBook() method
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Get all books call Success", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to get book data by id
    @GetMapping("/get/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") int bookId) {
        Book book = bookService.getBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("GetBookById call Success", book);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to delete book data by id
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("bookId") int bookId) {
        bookService.deleteBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("deleted successfully", bookId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to get book data by BookName
    @GetMapping("/getbook/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByBookName(@PathVariable("bookName") String bookName) {
        List<Book> books = null;
        books = bookService.getBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Get book by searching BookName is successful!", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to update book data by id
    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookByID(@PathVariable int bookId, @Valid @RequestBody BookDTO bookDTO) {
        Book updatedBooks = bookService.updateBookById(bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Books are Updated by ID successfully", updatedBooks);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to sort book data in Ascending order
    @GetMapping("/sortingAsce")
    public ResponseEntity<ResponseDTO> sortingByAsce() {
        List<Book> books = null;
        books = bookService.sortingBookInAsce();
        ResponseDTO responseDTO = new ResponseDTO("Sorting Ascending call is successful! ", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to sort book data in decending order
    @GetMapping("/sortingDesc")
    public ResponseEntity<ResponseDTO> sortingByDesce() {
        List<Book> books = null;
        books = bookService.sortingBookInDesc();
        ResponseDTO responseDTO = new ResponseDTO("Sorting decending call is successful! ", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
//    Ability to get book data AutherName
    @GetMapping("/getauthor/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable("authorName") String authorName) {
        List<Book> books = null;
        books = bookService.getBookByAuthorName(authorName);
        ResponseDTO responseDTO = new ResponseDTO("Get book search by Author Name is successful!", books);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}