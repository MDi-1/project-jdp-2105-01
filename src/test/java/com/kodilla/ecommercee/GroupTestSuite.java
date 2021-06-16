package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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
@Transactional
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    GroupRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testGroupCreate() {
        // given
        Group group = new Group("test object - group 1");
        // when
        repository.save(group);
        Optional<Group> foundName = repository.findByName("test object - group 1");
        // then
        assertEquals("test object - group 1", foundName.get().getName());
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
    }

    @Test
    public void testGroupDelete() {
        //given
        Group group1 = new Group("test object - group Soft Commodities"); repository.save(group1);
        Group group2 = new Group("test object - group Hard Commodities"); repository.save(group2);
        Group group3 = new Group("test object - group Light Commodities"); repository.save(group3);
        Group group4 = new Group("test object - group Heavy Commodities"); repository.save(group4);
        //when
        repository.deleteByName("test object - group Light Commodities");
        //then
        assertTrue(repository.count() < 4);
    }

    @Test
    public void testFindAll() {
        //given
        Group group;
        //when
        for(int i = 0; i < 10; i ++) {
            String name = "test object - group" + i;
            group = new Group(name);
            repository.save(group);
        }
        //then
        assertEquals(10, repository.count());
    }

    @Test
    public void testAddProducts() {
        //given
        Product product1 = new Product("product1", "description1", 7.99);
        Product product2 = new Product("product2", "description2", 0.55);
        Product product3 = new Product("product3", "description3", 9.99);
        Group group = new Group("test object - group 11");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product2);

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        repository.save(group);

        //when
        List<Product> products = productRepository.findAll();

        //then
        assertEquals(3, products.size());
    }

    @Test
    public void testRemoveProducts() {
        //given
        Product product1 = new Product("product1", "description1", 7.99);
        Product product2 = new Product("product2", "description2", 0.55);
        Product product3 = new Product("product3", "description3", 9.99);
        Group group = new Group("test object - group 11");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product2);

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        repository.save(group);

        //when
        group.getProducts().remove(product1);
        productRepository.deleteByName("product1");
        group.getProducts().remove(product2);
        productRepository.deleteByName("product2");
        List<Product> products = productRepository.findAll();

        //then
        assertEquals(1, products.size());
    }

    @Test
    public void testProductsAfterGroupRemoval() {
        //given
        Product product1 = new Product("product1", "description1", 7.99);
        Product product2 = new Product("product2", "description2", 0.55);
        Product product3 = new Product("product3", "description3", 9.99);
        Group group = new Group("test object - group 11");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product2);

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        repository.save(group);

        //when
        System.out.println("\n" + group.getName());
        List<Product> products = productRepository.findAll();
        for(Product item: products) {
            System.out.println("id= " + item.getId() + "; name= " + item.getName());
        }
        //System.out.println("product name=" + productRepository.findById(1L).get().getName());
        repository.deleteById(group.getId());

        System.out.println("group removed \n" + products.size() + "\n");
        List<Product> products2 = productRepository.findAll();
        for(Product item: products2) {
            System.out.println("id= " + item.getId() + "; name= " + item.getName());
        }

        //then
        assertEquals(3, products2.size());
    }
}
