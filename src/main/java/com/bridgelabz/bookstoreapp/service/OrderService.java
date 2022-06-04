package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.Util.EmailSenderService;
import com.bridgelabz.bookstoreapp.Util.TokenUtility;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.Order;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    EmailSenderService mailService;
    @Autowired
    TokenUtility util;

    @Override
    public String insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderdto.getQuantity();
                book.get().setQuantity(quantity);
                bookRepo.save(book.get());
                int totalPrice = book.get().getPrice() * orderdto.getQuantity();
                Order newOrder = new Order(totalPrice, orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get(), orderdto.isCancel());
                orderRepo.save(newOrder);
                log.info("Order record inserted successfully");
                String token = util.createToken(newOrder.getOrderID());
                mailService.sendEmail(newOrder.getUser().getEmail(), "Test Email", "Registered SuccessFully, hii: "
                        + newOrder.getOrderID() + "Please Click here to get data-> "
                        + "http://localhost:8083/order/insert/" + token);
                log.info("Order record inserted successfully");
                return token;
            } else {
                throw new BookStoreException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    @Override
    public List<Order> getOrderRecord(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            List<Order> listOrder = orderRepo.findAll();
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("akshaysportive@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + order.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8083/order/getAllOrders/" + token);
            return listOrder;

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }

    @Override
    public List<Order> getAllOrderRecords(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> orderData = orderRepo.findById(id);
        if (orderData.isPresent()) {
            List<Order> listOrderData = orderRepo.findAll();
            log.info("ALL order records retrieved successfully");
            mailService.sendEmail("akshaysportive@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + orderData.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8098/order/getById" + token);
            return listOrderData;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }

    }

    @Override
    public Order cancelOrder(String token, int userId) {
        Integer id=util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        Optional<UserRegistration> user = userRepo.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepo.save(order.get());
            mailService.sendEmail(order.get().getUser().getEmail(), "Test Email", "canceled order SuccessFully, hii: "
                    +order.get().getOrderID()+"Please Click here to get data of updated id-> "
                    +"http://localhost:8098/order/cancelOrder/"+token);
            return order.get();
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
}
