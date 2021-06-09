package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}
