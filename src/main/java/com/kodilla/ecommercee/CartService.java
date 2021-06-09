package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

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
