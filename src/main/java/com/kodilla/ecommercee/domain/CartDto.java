package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private double value;
    private List<String> productsList;
    private String name;
}