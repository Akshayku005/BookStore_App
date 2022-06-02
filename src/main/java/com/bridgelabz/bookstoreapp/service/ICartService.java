package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;


public interface ICartService {
    Cart insertItems(CartDTO cartdto);

    ResponseDTO getCartDetails();

    Cart getCartDetailsById(Integer cartId);

    Cart deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);


}
