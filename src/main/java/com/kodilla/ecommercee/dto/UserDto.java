package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class UserDto {
    private final Long id;
    private final int key;
    private final String name;
}
