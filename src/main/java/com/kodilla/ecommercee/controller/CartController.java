package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.DTo.CartDto;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @GetMapping("/getCart/{cartId}")
    public CartDto getCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        return cartMapper.mapToCartDto(cart);
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public void deleteCart(@PathVariable Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        cart.getProducts().clear();
        cartService.saveCart(cart);
    }

    @PutMapping("/addProduct/{cartId}/{productId}/{quantity}")
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

    @DeleteMapping("/deleteProductFromCart/{cartId}/{productId}")
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
    public Long createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cart);
        System.out.println("Empty Cart with id: "+ cart.getId()+ " was created.");
        return cart.getId();
    }

    @PostMapping("/createOrderFromCart/{cartId}")
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

