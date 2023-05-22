package com.adebisi.task_seven.repository;

import com.adebisi.task_seven.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepo extends JpaRepository<Wish, Long> {



    Optional<Wish>  findWishByNameAndCustomer_Email(String name, String email);
}
