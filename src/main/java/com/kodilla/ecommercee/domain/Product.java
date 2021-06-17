package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")

public class Product {

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String description, double price, Group group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "products"
    )
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "products"
    )
    private List<Order> orders = new ArrayList<>();
}
