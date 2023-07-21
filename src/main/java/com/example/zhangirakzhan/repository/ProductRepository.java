package com.example.zhangirakzhan.repository;

import com.example.zhangirakzhan.entity.Product;
import com.example.zhangirakzhan.entity.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
