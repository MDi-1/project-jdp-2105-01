package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ProductDto {

    private final Long id;
    private final String name;
    private final String description;
    private final double price;
    private final Long group_id;
}
