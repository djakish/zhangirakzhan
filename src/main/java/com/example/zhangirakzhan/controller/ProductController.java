package com.example.zhangirakzhan.controller;


import com.example.zhangirakzhan.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }
}
