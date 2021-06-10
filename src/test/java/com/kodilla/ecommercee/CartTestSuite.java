package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartTestSuite {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindById() {
        //Given
        Cart cart = new Cart("Cart1");

        //When
        cartRepository.save(cart);
        Long id = cart.getId();

        //Then
        Optional<Cart> cartOptional = cartRepository.findById(id);
        assertEquals(id, cartOptional.get().getId());

        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDeleteCart() {
        //Given
        Cart cart = new Cart("Cart1");

        //When
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());

        //Then
        List<Cart> cartList = cartRepository.findAll();
        assertEquals(0, cartList.size());
    }

    @Test
    public void testDeleteProductFromCart() {
        //Given
        Product product1 = new Product("Product1", "Description1", 10.00);
        Product product2 = new Product("Product2", "Description2", 20.00);
        Cart cart = new Cart("Cart1");

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.deleteByName(product1.getName());

        //Then
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
    }

    @Test
    public void testUpdateCart() {
        //Given
        Product product1 = new Product("Product1", "Description1", 7.50);
        Product product2 = new Product("Product2", "Description2", 12.30);
        Cart cart = new Cart("Cart1");

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);


        //Then
        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testProductIsPresentWhenCartDeleted() {
        //Given
        User user = new User("User1");
        Cart cart = new Cart("Cart1");
        Product product1 = new Product("Product1", "Description1", 13.70);
        Product product2 = new Product("Product2", "Description2", 20.00);

        cart.setUser(user);
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);


        //When
        cartRepository.save(cart);
        cartRepository.deleteById(cart.getId());

        //Then
        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());

        //CleanUp
        productRepository.deleteAll();
    }

    @Test
    public void testUserWhenCartDeleted() {
        //Given
        Cart cart = new Cart("Cart1");
        User user = new User("User1");
        cart.setUser(user);

        //When
        cartRepository.save(cart);
        userRepository.save(user);
        cartRepository.deleteById(cart.getId());

        //Then
        List<User> users = userRepository.findAll();
        assertEquals(user, users.get(0));

        //CleanUp
        userRepository.deleteAll();
    }
}
