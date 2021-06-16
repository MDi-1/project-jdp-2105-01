package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    private UserRepository userRepository;

    public Cart mapToCart(final CartDto cartDto) {

        return new Cart(
                cartDto.getId(),
                userRepository.getOne(cartDto.getUserId()), cartDto.getProducts());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getValue(),
                cart.getProducts(),
                cart.getName());
    }
}
