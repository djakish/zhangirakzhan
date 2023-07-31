package com.example.zhangirakzhan.controller;


import com.example.zhangirakzhan.entity.Product;
import com.example.zhangirakzhan.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getOrders() {
        List<Product> body = productService.getProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addOrder(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
