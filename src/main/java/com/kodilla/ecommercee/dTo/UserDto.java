package com.kodilla.ecommercee.dTo;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private int key;
    private String name;
}
