package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.DTo.CartDto;
import com.kodilla.ecommercee.domain.Cart;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CartService {
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public Optional<Cart> getCart(final Long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(final CartDto cartDto) {
        cartRepository.deleteById(cartDto.getId());
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }
}
