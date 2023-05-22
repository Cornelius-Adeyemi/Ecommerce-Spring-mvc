package com.adebisi.task_seven.service;


import com.adebisi.task_seven.model.Wish;
import com.adebisi.task_seven.repository.WishListRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishListService {

    private WishListRepo wishListRepo;

    public WishListService(WishListRepo wishListRepo){
        this.wishListRepo = wishListRepo;
    }

   public Optional<Wish> findByNameAndCustomer_Email(String name, String email){


        return wishListRepo.findWishByNameAndCustomer_Email(name, email);
   }

   public void deleteAWishById(Long id){
        wishListRepo.deleteById(id);
   }
}
