package com.kodilla.ecommercee.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Cart {
    private Long id;
    private double value;
    private List<String> productsList;
    private String name;
}
