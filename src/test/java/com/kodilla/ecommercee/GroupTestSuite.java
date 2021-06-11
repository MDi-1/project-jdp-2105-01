package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    GroupRepository repository;

    @Test
    public void testGroupCreate() {
        // given
        Group group = new Group("test object - group 1");
        // when
        repository.save(group);
        Long id = group.getId();
        Optional<Group> foundName = repository.findByName("test object - group 1");
        // then
        assertEquals("test object - group 1", foundName.get().getName());
        // cleanup
        repository.deleteAll();
    }

    @Test
    public void testGroupFind() {
        //given
        Group group = new Group("test object - group 2");
        //when
        repository.save(group);
        Long id = group.getId();
        Optional<Group> foundById = repository.findById(id);
        //then
        assertEquals(id, foundById.get().getId());
        // cleanup
        repository.deleteAll();
    }

    @Test
    public void testGroupUpdate() {
        //given
        Group group = new Group("test object - group 3");
        //when
        repository.save(group);
        Long id = group.getId();
        Optional<Group> found = repository.findById(id);
        found.get().setName("name has been modified");
        //then
        assertNotEquals("test object - group 3", found.get().getName());
        // cleanup
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGroupDelete() {
        //given
        Group group = null;
        for(int i = 1; i < 5; i ++) {
            String name = "test object - group" + i;
            group = new Group(name);
            repository.save(group);
        }
        List<Group> foundItems = repository.findAll();
        for(Group item: foundItems) {
            System.out.println(item.getName());
        }
        repository.deleteByName("test object - group Light Commodities");
        //then
        assertTrue(repository.count() < 4);
        // cleanup
        repository.deleteAll();
    }
}
