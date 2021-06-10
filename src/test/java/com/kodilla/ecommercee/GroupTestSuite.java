package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class GroupTestSuite {

    @Autowired
    GroupRepository repository;

    @Test
    public void testGroupCreate() {
        // given
        Group group = new Group(123L, "test group 3");
        // when
        repository.save(group);
        Long id = group.getId();
        // then
        Optional<Group> testId = repository.findById(id);
        assertTrue(testId.isPresent());
        // cleanup
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
