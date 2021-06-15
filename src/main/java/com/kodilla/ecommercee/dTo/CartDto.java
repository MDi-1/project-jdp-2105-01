package com.kodilla.ecommercee.dTo;

import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private Long userId;
    private double value;
    private List<Product> products;
    private String name;
}
