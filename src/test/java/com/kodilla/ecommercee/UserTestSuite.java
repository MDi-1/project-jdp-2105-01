package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
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
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveUser() {
        //Given
        User user = new User(1L, 1234, "User1");

        //When
        Long id = user.getId();
        userRepository.save(user);

        //Then
        Optional<User> userOptional = userRepository.findById(user.getId());
        assertEquals(id, userOptional.get().getId());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testFindUser() {
        //Given
        User user = new User("User1");

        //When
        userRepository.save(user);

        //Then
        Optional<User> userOptional = userRepository.findById(user.getId());
        assertTrue(userOptional.isPresent());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUpdateUser() {
        //Given
        User user = new User("User1");

        //When
        userRepository.save(user);
        Long userId = user.getId();
        User updateUser = userRepository.getOne(userId);
        updateUser.setName("UserUpdate");
        userRepository.save(updateUser);
        Long updateUserId = updateUser.getId();

        //Then
        assertEquals(userId, updateUserId);
        assertEquals("UserUpdate", updateUser.getName());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserDelete() {
        //Given
        User user = new User("User1");

        //When
        userRepository.save(user);
        userRepository.deleteById(user.getId());

        //Then
        assertFalse(userRepository.existsById(user.getId()));
    }

    @Test
    public void testCartWhenUserDeleted() {
        //Given
        User user = new User("User1");
        Cart cart = new Cart("Cart1");
        cart.setUser(user);

        //When
        userRepository.save(user);
        cartRepository.save(cart);
        userRepository.deleteById(user.getId());

        //Then
        List<Cart> carts = cartRepository.findAll();
        assertEquals(cart, carts.get(0));

        //CleanUp
        cartRepository.deleteAll();
    }

    @Test
    public void testOrderWhenUserDeleted() {
        //Given
        User user = new User("User1");
        Order order = new Order("Order1", "Send");
        order.setUser(user);

        //When
        userRepository.save(user);
        orderRepository.save(order);
        userRepository.deleteById(user.getId());

        //Then
        List<Order> orders = orderRepository.findAll();
        assertEquals(order, orders.get(0));

        //CleanUp
        orderRepository.deleteAll();
    }
}
