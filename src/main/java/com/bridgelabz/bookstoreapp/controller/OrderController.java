package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Order;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order newOrder = orderService.insertOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order placed successfully !", newOrder);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords() {
        List<Order> newOrder = orderService.getAllOrderRecords();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !", newOrder);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id) {
        Order newOrder = orderService.getOrderRecord(id);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved successfully !", newOrder);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody OrderDTO orderdto) {
        Order newOrder = orderService.updateOrderRecord(id, orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Record updated successfully !", newOrder);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id) {
        Order newOrder = orderService.deleteOrderRecord(id);
        ResponseDTO responseDTO = new ResponseDTO("Record deleted successfully !", newOrder);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.ACCEPTED);
    }
}
