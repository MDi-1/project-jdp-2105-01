package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;
}
