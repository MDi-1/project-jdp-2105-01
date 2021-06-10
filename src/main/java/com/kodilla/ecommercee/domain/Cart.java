package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @ManyToMany
    private List<Product> products;




}