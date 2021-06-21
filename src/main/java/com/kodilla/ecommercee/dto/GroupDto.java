package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class GroupDto {

    private final Long id;
    private final String name;
    private List<Long> productIds;

    public GroupDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
