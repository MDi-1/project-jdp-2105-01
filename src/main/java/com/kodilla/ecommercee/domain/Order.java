package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_USER")
public class Order {

    public Order(Long id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public Order(String name, String orderStatus) {
        this.name = name;
        this.orderStatus = orderStatus;
    }


    public Order(@NotNull String name) {
        this.name = name;
    }

    public Order(Long id, String name, String orderStatus) {
        this.id = id;
        this.name = name;
        this.orderStatus = orderStatus;

    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;


    @Column(name = "NAME")
    private String name;


    @Column(name = "STATUS")
    private String orderStatus;


    @Column(name = "VALUE")
    private double value;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_FK")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();
}
