package com.adebisi.task_seven.repository;

import com.adebisi.task_seven.DTO.CustomerDTO;
import com.adebisi.task_seven.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {


    Optional<Customer> findCustomerByEmail(String email);



     Optional<Customer>  findCustomerByEmailAndPassword(String email, String password);


}
