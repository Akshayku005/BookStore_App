package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book insertBook(BookDTO bookDTO) {
        Book book = null;
        book = new Book(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new BookStoreException("Book with this id " + bookId + " doest not exists!"));
    }

    @Override
    public void deleteBookById(int bookId) {
        Book book = this.getBookById(bookId);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getBookByName(String bookName) {
        return bookRepository.findBookByName(bookName);
    }

    @Override
    public Book updateBookById(int bookId, BookDTO bookDTO) {
        Book book = this.getBookById(bookId);
        book.updateBook(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> sortingBookInAsce() {
        return bookRepository.sortingInAsce();
    }

    @Override
    public List<Book> sortingBookInDesc() {
        return bookRepository.sortingInDesc();
    }
}
