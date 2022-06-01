package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book_store where bookName = :bookName", nativeQuery = true)
    List<Book> findBookByName(String bookName);
    @Query(value = "select *from book_store order by bookName asc", nativeQuery = true)
    List<Book> sortingInAsce();
    @Query(value = "select *from address_book_table order by bookName desc ", nativeQuery = true)
    List<Book> sortingInDesc();
}
