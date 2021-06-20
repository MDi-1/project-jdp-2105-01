package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private String name;
    private String orderStatus;
    private Long userId;
    private double value;



}




