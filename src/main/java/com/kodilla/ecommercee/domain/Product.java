package com.kodilla.ecommercee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
<<<<<<< HEAD


@Entity(name = "PRODUCTS")

=======
@Entity(name = "PRODUCTS")
>>>>>>> 90cddb1fa15b75319bd90aa635f3e990bf184842
public class Product {

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue
    @NotNull
<<<<<<< HEAD


    @Column(name = "PRODUCT_ID", unique = true)

=======
    @Column(name = "PRODUCT_ID", unique = true)
>>>>>>> 90cddb1fa15b75319bd90aa635f3e990bf184842
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
<<<<<<< HEAD

=======
>>>>>>> 90cddb1fa15b75319bd90aa635f3e990bf184842

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "products"
    )
<<<<<<< HEAD
    private List<Order> orders = new ArrayList<>();
=======
    private List<Order> orders;
>>>>>>> 90cddb1fa15b75319bd90aa635f3e990bf184842

    public Product( Long id,  String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;

<<<<<<< HEAD
    }



=======
    private List<Order> orders = new ArrayList<>();
>>>>>>> 90cddb1fa15b75319bd90aa635f3e990bf184842
}
