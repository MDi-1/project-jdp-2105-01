package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRITION")
    private String description;

    @Column(name = "PRICE")
    private double price;
}