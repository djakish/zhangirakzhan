package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getProducts();
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void removeProduct(Product product);
}
