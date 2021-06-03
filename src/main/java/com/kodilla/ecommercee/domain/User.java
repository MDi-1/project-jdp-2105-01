package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "KEY")
    private int key;

    @Column(name = "NAME")
    private String name;
}
