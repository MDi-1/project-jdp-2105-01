package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserTestSuite {

    @Autowired
    UserRepository userRepository;



    @Test
    public void testUserFindAll(){
//        Given
        User user1 = new User(1L, 324, "TestName");
        User user2 = new User(2L, 1424, "TestName2");
//        When
        userRepository.save(user1);
        userRepository.save(user2);
        Long idUser1 = user1.getId();
        Long idUser2 = user2.getId();
//        Then
        assertEquals(2, userRepository.findAll().size());

        //        Clean Up
        userRepository.deleteById(idUser1);
        userRepository.deleteById(idUser2);
    }

    @Test
    public void testUserCreate(){
//        Given
        User user = new User(1L,232,"CreateName");
//        When
        userRepository.save(user);
        Long id = user.getId();
//        Then
        Optional<User> testId = userRepository.findById(id);
        assertTrue(testId.isPresent());

//        Clean Up
        userRepository.deleteById(id);

    }

    @Test
    public void testUserDeleteById(){
//        Given
        User user = new User(1L, 5253, "DeleteName");
//        When
        userRepository.save(user);
        long id = user.getId();
//        Then
        userRepository.deleteById(id);
        Optional<User> deleteUser = userRepository.findById(id);
        assertFalse(deleteUser.isPresent());
    }


}
