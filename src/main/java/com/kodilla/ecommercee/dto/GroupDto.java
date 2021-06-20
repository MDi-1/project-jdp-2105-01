package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GroupDto {

    private final Long id;
    private final String name;
    // czy może dopisać tu listę produktów?
}
