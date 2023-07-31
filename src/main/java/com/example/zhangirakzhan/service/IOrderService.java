package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Order;

import java.util.List;

public interface IOrderService {
    public Order placeOrder(Order order);
    public List<Order> getUserOrders(Long userId);
    public void cancelOrder(Long orderId);
}
