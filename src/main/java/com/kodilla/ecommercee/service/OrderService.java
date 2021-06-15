package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.mapToOrderDtoList(orders);
    }

    public Optional<Order> getOrder(final Long id) {
        return orderRepository.findById(id);
    }

    public void createOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) throws OrderNotFoundException {
        return orderRepository.findById(id)
                .map(order -> {
                    Order updatedOrder = null;
                    try {
                        updatedOrder = updateOrderFields(orderDto, order);
                    } catch (OrderNotFoundException e) {
                        e.printStackTrace();
                    }
                    orderRepository.save(updatedOrder);
                    return orderMapper.mapToOrderDto(updatedOrder);
                })
                .orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
    }

    private Order updateOrderFields(OrderDto orderDto, Order order) throws OrderNotFoundException {
        if (orderDto.getId() != null) {
            order.setUser(userRepository.findById(orderDto.getId())
                    .orElseThrow(() -> new OrderNotFoundException("User with id: " + orderDto.getId() + " not found")));
        }
        if (orderDto.getOrderStatus() != null) {
            order.setOrderStatus(orderDto.getOrderStatus());
        }
        return order;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
