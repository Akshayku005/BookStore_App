package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;

import java.util.List;


public interface ICartService {
   String  insertItems(CartDTO cartdto);

List<Cart> getCartDetails(String token);

    Cart getCartDetailsById(String token);

    void deleteCartItemById(String token);

    Cart updateRecordById(String token, CartDTO cartDTO);

}


