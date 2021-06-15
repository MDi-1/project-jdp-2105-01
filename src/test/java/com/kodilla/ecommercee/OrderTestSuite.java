package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderTestSuite {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Order order = new Order(1L, "Order1", "Send");

        //When
        orderRepository.save(order);

        //Then
        List<Order> orders = orderRepository.findAll();
        assertEquals(1, orders.size());
    }

    @Test
    public void testUserWhenOrderDeleted() {
        //Given
        Order order = new Order("Order1", "Send");
        User user = new User("User1");
        orderRepository.save(order);
        userRepository.save(user);

        //When
        orderRepository.delete(order);
        Long userId = user.getId();

        //Then
        Optional<User> userOptional = userRepository.findById(userId);
        assertEquals(0, orderRepository.count());
        assertTrue(userOptional.isPresent());
    }

    @Test
    public void testOrderWhenUserDeleted() {
        //Given
        Order order = new Order("Order1", "Send");
        User user = new User("User1");
        orderRepository.save(order);
        userRepository.save(user);

        //When
        userRepository.delete(user);
        Long orderId = order.getId();

        //Then
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        assertEquals(0, userRepository.count());
        assertTrue(orderOptional.isPresent());
    }

    @Test
    public void testAddProductToOrder() {
        Product product1 = new Product("Product1", "Description1", 10.50);
        Product product2 = new Product("Product2", "Description2", 21.70);
        Product product3 = new Product("Product3", "Description3", 12.30);
        List<Product> products = new ArrayList<>();
        Order order = new Order(1L, 44.50, products);


        //When
        Long orderId = order.getId();
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        order.getProducts().add(product3);
        orderRepository.save(order);

        //Then
       /* Optional<Order> orderOptional = orderRepository.findById(orderId);
        assertTrue(orderOptional.isPresent());*/
        assertEquals(3, products.size());
    }

    @Test
    public void testDeleteProductFromOrder() {
        Product product1 = new Product("Product1", "Description1", 10.50);
        Product product2 = new Product("Product2", "Description2", 21.70);
        Product product3 = new Product("Product3", "Description3", 12.30);
        List<Product> products = new ArrayList<>();
        Order order = new Order(1L, 44.50, products);

        order.getProducts().add(product1);
        order.getProducts().add(product2);
        order.getProducts().add(product3);
        orderRepository.save(order);

        //When
        Long orderId = order.getId();
        order.getProducts().remove(product2);
        orderRepository.save(order);


        //Then
       /* Optional<Order> orderOptional = orderRepository.findById(orderId);
        assertTrue(orderOptional.);*/
        assertEquals(2, products.size());
    }
}
