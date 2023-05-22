package com.adebisi.task_seven.repository;

import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

  Optional<Cart> findCartByNameAndCustomer_Email(String name, String email);

  @Modifying
  @Transactional
  @Query(value="DELETE FROM cart WHERE customer_id=?1", nativeQuery = true)
  void deleteAllByCustomer_Id(Long id);

  void deleteAllByCustomer(Customer customer);
}
