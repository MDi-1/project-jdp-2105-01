package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    GroupRepository repository;

    @Test
    public void testGroupCreate() {
        // given
        Group group = new Group(444L, "test group 3");
        // when
        repository.save(group);
        Long id = group.getId();
        // then
        Optional<Group> testId = repository.findById(id);
        System.out.println("\nid= "+ id + "; testId= "+ testId +"\n");
        assertEquals(id, repository.findAll());
        // cleanup
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
