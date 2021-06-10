package com.kodilla.ecommercee.dTo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private String orderStatus;
}
