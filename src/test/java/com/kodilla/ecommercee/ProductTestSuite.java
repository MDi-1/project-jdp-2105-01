package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void testUpdateProduct() {

        //Given
        Product product = new Product(1L, "Product1", "Product", 22);
        Product product1 = new Product(1L, "Prot1", "Prot", 22);

        //When
        productRepository.save(product);

        //Then
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
    }

    @Test
    public void testFindById() {

        //Given
        Product product = new Product("Product_1", "Product", 22);

        //When
        productRepository.save(product);
        Long id = product.getId();

        //Then
        ArrayList<Product> products = new ArrayList<>();
        products.add(productRepository.getOne(id));

        assertEquals(id, products.get(0).getId());

        productRepository.delete(product);
    }

    @Test
    public void testDeleteProduct() {

        //Given
        Product product = new Product("Product_1", "Product", 22);

        //When
        productRepository.save(product);
        productRepository.delete(product);

        //Then
        List<Product> products = productRepository.findAll();
        assertEquals(0, products.size());

        productRepository.delete(product);
    }

    @Test
    public void testProductsAddToObjectCart() {

        //Given
        Product product1 = new Product("Product1", "Product1", 22);
        Product product2 = new Product("Product2", "Product2", 21);

        Cart cart = new Cart("Cart");

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        //Then
        assertEquals(2, cart.getProducts().size());

        productRepository.delete(product1);
        productRepository.delete(product2);
    }

    @Test
    public void testProductsAddToObjectOrder() {

        //Given
        Product product1 = new Product("Product1", "Product1", 22);
        Product product2 = new Product("Product2", "Product2", 21);

        Order order = new Order("Order");

        //When
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        orderRepository.save(order);

        //Then
        assertEquals(2, order.getProducts().size());

        productRepository.delete(product1);
        productRepository.delete(product2);
    }

    @Test
    public void testCartIsPresentWhenProductDelete() {

        //Given
        Product product1 = new Product("Product1", "Product1", 22);
        Product product2 = new Product("Product2", "Product2", 21);

        Cart cart = new Cart("Cart");

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        productRepository.save(product1);
        productRepository.save(product2);

        productRepository.delete(product1);
        productRepository.delete(product2);

        //Then
        List<Cart> carts = cartRepository.findAll();
        assertEquals(1, carts.size());

        productRepository.delete(product1);
        productRepository.delete(product2);
    }

    @Test
    public void testOrderIsPresentWhenProductDelete() {

        //Given
        Product product1 = new Product("Product1", "Product1", 22);
        Product product2 = new Product("Product2", "Product2", 21);

        Order order = new Order("Order");

        //When
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        orderRepository.save(order);

        productRepository.save(product1);
        productRepository.save(product2);

        productRepository.delete(product1);
        productRepository.delete(product2);

        //Then
        List<Order> orders = orderRepository.findAll();
        assertEquals(1, orders.size());

        productRepository.delete(product1);
        productRepository.delete(product2);
    }
}




