package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.Util.EmailSenderService;
import com.bridgelabz.bookstoreapp.Util.TokenUtility;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    //Ability to adding books with user name to the cart
    @Override
    public String insertItems(CartDTO cartdto) {
        Optional<Book> book = bookRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            cartRepository.save(newCart);
            String token = util.createToken(newCart.getCartId());
            return token;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    //Ability to getAll CartDetails by token
    @Override
    public List<Cart> getCartDetails(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> cartData = cartRepository.findById(id);
        if (cartData.isPresent()) {
            List<Cart> listOfCartdata = cartRepository.findAll();
            log.info("ALL cart records retrieved successfully");
            return listOfCartdata;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    //Ability to get cart details by token
    @Override
    public Cart getCartDetailsById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> CartData = cartRepository.findById(id);
        if (CartData.isPresent()) {
            return CartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    //Ability to delete cart using token
    @Override
    public void deleteCartItemById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> delete = cartRepository.findById(id);
        if (delete.isPresent()) {
            cartRepository.deleteById(id);
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }
    }

    //Ability to update cart by using token
    @Override
    public Cart updateRecordById(String token, CartDTO cartDTO) {
        int id = util.decodeToken(token);
        Optional<Cart> cart = cartRepository.findById(id);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart cartData = new Cart(id, cartDTO.getQuantity(), book.get(), user.get());
                cartRepository.save(cartData);
                log.info("Cart record updated successfully for id " + id);
                return cartData;
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }
}

