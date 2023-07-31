package com.example.zhangirakzhan.controller;


import com.example.zhangirakzhan.entity.Order;
import com.example.zhangirakzhan.entity.Product;
import com.example.zhangirakzhan.entity.User;
import com.example.zhangirakzhan.service.OrderService;
import com.example.zhangirakzhan.service.TokenService;
import com.example.zhangirakzhan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(
            OrderService orderService,
            UserService userService
    ) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrders() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var name = authentication.getName();
        var user = userService.findByUsername(name);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Order> body = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var name = authentication.getName();
        var user = userService.findByUsername(name);

        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        order.setUser(user);
        Order savedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
}
