package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CART_ID", unique = true)
    private Long id;

    @Column(name = "VALUE")
    private double value;

    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "USER")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Product> products;
}