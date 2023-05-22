package com.adebisi.task_seven.service;

import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    CartRepo cartRepo;
    @Autowired
    public CartService(CartRepo cartRepo){
        this.cartRepo = cartRepo;
    }


    public Optional<Cart> findCartByNameAndCustomer_Email(String name, String email){

        return cartRepo.findCartByNameAndCustomer_Email(name, email);
    }

    public void save(Cart cart){
        cartRepo.save(cart);
    }

    public void deleteProductInCart(Long id){
        cartRepo.deleteById(id);
    }

    public void deleteAllById(Long id){
        cartRepo.deleteAllByCustomer_Id(id);
    }

    public void deleteAllByCustomer(Customer customer){
        cartRepo.deleteAllByCustomer(customer);
    }
}
