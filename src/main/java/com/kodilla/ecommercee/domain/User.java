package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "USER_KEY", unique = true)
    private int key;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BLOCKED")
    private boolean isBlocked;

    @JoinColumn(name = "CART_ID")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;
}
