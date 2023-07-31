package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Product;

public interface IProductService {
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void removeProduct(Product product);
}
