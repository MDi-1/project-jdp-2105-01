package com.kodilla.ecommercee.dTo;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;

}