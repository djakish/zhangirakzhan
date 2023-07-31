package com.example.zhangirakzhan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Id
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotEmpty
    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();
}
