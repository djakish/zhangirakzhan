package com.example.zhangirakzhan.repository;

import com.example.zhangirakzhan.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

}
