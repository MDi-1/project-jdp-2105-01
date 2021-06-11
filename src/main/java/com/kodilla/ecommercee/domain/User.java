package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {

    public User(String name) {
        this.name = name;
    }

    public User(Long id,int key, String name) {
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

    @Column(name = "GENERATED_KEY")
    private LocalDateTime generatedKey;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "BLOCKED")
    private boolean isBlocked;

    @JoinColumn(name = "CART_ID")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
