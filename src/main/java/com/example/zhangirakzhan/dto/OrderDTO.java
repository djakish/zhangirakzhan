package com.example.zhangirakzhan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDTO {
    private LocalDate orderDate;
    private double quantity;
    private Long productId;
}
