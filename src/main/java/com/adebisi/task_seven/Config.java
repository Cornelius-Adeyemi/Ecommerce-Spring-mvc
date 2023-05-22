package com.adebisi.task_seven;


import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepo customerRepo){
        return (args)->{

//            Customer customer = customerRepo.findCustomerByEmail("aabbii@gmail.com").get();
//
//            Cart cart1 = new Cart();
//            cart1.setName("book");
//            cart1.setQuantity(4);
//            cart1.setPrice(50);
//            Cart cart2 = new Cart();
//            cart2.setName("cake");
//
//            cart2.setPrice(50);
//
//
//            cart2.setCustomer(customer);
//            cart1.setCustomer(customer);
//            customer.getCartList().add(cart1);
//            customer.getCartList().add(cart2);
//
//            customerRepo.save(customer);
        };
    }
}
