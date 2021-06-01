package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/cart/")
public class CartController {

    @PostMapping(value = "createEmptyCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEmptyCart(@PathVariable Long userId) {
       //do nothing
    }

    @PatchMapping("getProductFromEmptyCart/")
    public void getProductFromEmptyCart(@RequestParam Long cartId) {
        System.out.println("You have no products in Your cart");
    }

    @PutMapping("addProduct")
    public CartDto addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) {
        return new CartDto(1L, 500, Arrays.asList("Product1", "Product2"), "Cart1");
    }

    @DeleteMapping("deleteProduct")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        // do nothing
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createAnOrderBasedOnACart(@RequestBody CartDto cartDto) {
        //do nothing
    }
}
