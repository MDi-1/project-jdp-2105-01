
package com.kodilla.ecommercee.dTo;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private String name;
    private String orderStatus;
    private User userId;
    private double value;


}




