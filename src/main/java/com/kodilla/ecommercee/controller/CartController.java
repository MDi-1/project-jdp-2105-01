package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        return cartMapper.mapToCartDto(cart);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
    }

    @PutMapping("/{cartId}/{productId}/{quantity}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartDto addProductToCart(@PathVariable Long cartId,
                                    @PathVariable Long productId,
                                    @PathVariable Integer quantity) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId)
                .orElseThrow(CartNotFoundException::new);
        Product product = productRepository.getOne(productId);
        cart.getProducts().add(product);
        Cart cartUpdated = cartService.saveCart(cart);
        System.out.println(quantity + "product/s with id " + product + " added to cart.");
        return cartMapper.mapToCartDto(cartUpdated);
    }

    @DeleteMapping("/{cartId}/{productId}/{deleteProduct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CartDto deleteProductFromCart(@PathVariable Long cartId,
                                         @PathVariable Long productId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId)
                .orElseThrow(CartNotFoundException::new);
        Product product = productRepository.getOne(productId);
        cart.getProducts().remove(product);
        Cart cartUpdated = cartService.saveCart(cart);
        System.out.println("Product with id "+ productId+ " was deleted.");
        return cartMapper.mapToCartDto(cartUpdated);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @PostMapping("/{id}/{cartId}/{createOrderFromCart}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        Order order = new Order(cart.getUser().getId(),
                cart.getValue(),
                cart.getProducts());
        System.out.println("Order for User: " + cart.getUser().getId() + " value = "
                + cart.getValue() + " includes products: " + cart.getProducts());
        cartService.saveOrder(order);
    }
}

