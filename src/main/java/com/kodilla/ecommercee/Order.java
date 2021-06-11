package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
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

    public Order(String name, String orderStatus) {
        this.name = name;
        this.orderStatus = orderStatus;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "STATUS")
    private String orderStatus;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
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
