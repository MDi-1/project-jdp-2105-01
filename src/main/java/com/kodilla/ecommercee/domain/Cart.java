package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;
}