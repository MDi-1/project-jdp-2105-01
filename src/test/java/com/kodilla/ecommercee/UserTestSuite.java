package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;


    @Test
    public void testUserFindAll() {
//        Given
        User user = new User("TestName");

//        When
        userRepository.save(user);
        Long id = user.getId();

//        Then
        assertEquals(1, userRepository.findAll().size());
        //        Clean Up
        try{
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testUserCreate() {
//        Given
        User user = new User("CreateName");

//        When
        userRepository.save(user);
        Long id = user.getId();

//        Then
        Optional<User> testId = userRepository.findById(id);
        assertTrue(testId.isPresent());

//        Clean Up
        try{
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void testUserDeleteById(){
//        Given
        User user = new User("DeleteName");

//        When
        userRepository.save(user);
        Long id = user.getId();

//        Then
        userRepository.deleteById(id);
        Optional<User> deleteUser = userRepository.findById(id);
        assertFalse(deleteUser.isPresent());
    }

    @Test
    public void testRelationUserCart() {
//            Given
        User user = new User("UserName");
        userRepository.save(user);
        Long id = user.getId();
//        When
        Optional<Cart> testCart = cartRepository.findById(user.getCart().getId());
//        Then
        assertTrue(testCart.isPresent());
//        Clean Up

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
