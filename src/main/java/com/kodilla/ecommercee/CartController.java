package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping(value = "createEmptyCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cart createEmptyCart(CartDto cartDto) {
        return new Cart();
    }

    @PatchMapping("/{id}/getProductFromEmptyCart/")
    public void getProductFromEmptyCart() {
        System.out.println("You have no products in Your cart");
    }

    @PutMapping("cart/addProduct")
    public CartDto addProductToCart(CartDto cartDto) {
        return new CartDto(1L, 500, Arrays.asList("Product1", "Product2"), "Cart1");
    }

    @DeleteMapping("cart/deleteProduct")
    public void deleteProductFromCart(Long id) {
        // do nothing
    }

    @PostMapping(value = "cart/createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cart createAnOrderBasedOnACart() {
        return new Cart();
    }
}
