package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartMapper {
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public Cart mapToCart(final CartDto cartDto){
        return new Cart(
                cartDto.getId(),
                userRepository.getOne(cartDto.getUserId()),
                cartDto.getProductId().stream()
                        .map(productRepository::findById)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getUser().getId(),
                cart.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList())
                );
    }
}
