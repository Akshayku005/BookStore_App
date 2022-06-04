package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.Order;

import java.util.List;

public interface IOrderService {
    String insertOrder(OrderDTO orderdto);

    List<Order> getOrderRecord(String token);

    List<Order> getAllOrderRecords(String token);


    Order cancelOrder(String token, int userId);

}
