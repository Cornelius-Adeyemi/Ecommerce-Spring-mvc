package com.adebisi.task_seven.repository;

import com.adebisi.task_seven.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);


}
