package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping (value = "getOrder")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
        return orderMapper.mapToOrderDto(order);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) throws OrderNotFoundException {
        return orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
