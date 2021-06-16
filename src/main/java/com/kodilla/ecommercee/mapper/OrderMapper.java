package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.domain.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(orderDto.getId(),
                orderDto.getName(),
                orderDto.getOrderStatus());
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(order.getId(),
                order.getName(),
                order.getOrderStatus(),
                order.getUser(),
                order.getValue());
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        return orders.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }
}
