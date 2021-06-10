
package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByName(String name);
}