package com.example.zhangirakzhan.service;

import com.example.zhangirakzhan.entity.Product;
import com.example.zhangirakzhan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Repository
@Transactional
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);

    }
    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
