package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long> {
}
