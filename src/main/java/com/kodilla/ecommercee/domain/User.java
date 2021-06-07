package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    public User(@NotNull Long id, @NotNull int key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;

    }


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

    @JoinColumn(name = "CART")
    @OneToOne
    private Cart cart;



}