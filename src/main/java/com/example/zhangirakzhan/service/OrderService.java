package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Order;
import com.example.zhangirakzhan.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class OrderService  implements IOrderService{
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public Order placeOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAll();
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
