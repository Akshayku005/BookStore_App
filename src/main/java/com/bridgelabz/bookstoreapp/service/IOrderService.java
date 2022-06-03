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
    Order insertOrder(OrderDTO orderdto);

    List<Order> getAllOrderRecords();

    Order getOrderRecord(Integer id);

    Order updateOrderRecord(Integer id, OrderDTO dto);

    Order deleteOrderRecord(Integer id);

}
