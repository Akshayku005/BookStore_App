package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;

    //Ability to adding books to the cart
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        String newCart = iCartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to getAll CartDetails by token
    @GetMapping("/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllCartDetails(@PathVariable String token) {
        List<Cart> cart = iCartService.getCartDetails(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success", cart);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //Ability to get cart details by token
    @GetMapping("/getById/{token}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable String token) {
        Cart specificCartDetail = iCartService.getCartDetailsById(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart details retrieved successfully", specificCartDetail);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to delete cart using token
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable String token) {
        iCartService.deleteCartItemById(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", token);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to update cart by using token
    @PutMapping("/updateById/{token}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable String token, @Valid @RequestBody CartDTO cartDTO) {
        Cart updateRecord = iCartService.updateRecordById(token, cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
}