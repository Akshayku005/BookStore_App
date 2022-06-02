package com.bridgelabz.bookstoreapp.service;

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

    @Override
    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            cartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = cartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " Not found Any Cart details ";
            dto.setMessage(message);
            dto.setData(0);
            return dto;
        } else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }

    @Override
    public Cart getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = cartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData.get();
        } else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public Cart deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = cartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            cartRepository.deleteById(cartId);
            return deleteData.get();
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, cartDTO.getQuantity(), book.get(), user.get());
                cartRepository.save(newCart);
                log.info("Cart record updated successfully for id " + cartId);
                return newCart;
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }
}

