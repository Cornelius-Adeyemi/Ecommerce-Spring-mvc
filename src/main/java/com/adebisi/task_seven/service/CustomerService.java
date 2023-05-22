package com.adebisi.task_seven.service;

import com.adebisi.task_seven.DTO.CustomerDTO;
import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo ){
        this.customerRepo = customerRepo;
    }


    public boolean findbyEmail(CustomerDTO customerDTO){
      Optional<Customer> customer = this.customerRepo.findCustomerByEmail(customerDTO.getEmail());

      return customer.isPresent();
    }

    public Customer returnCustomerByEmail(String email){
        Optional<Customer> customer = customerRepo.findCustomerByEmail(email);
        return customer.get();
    }

    public Customer findByEmailAndPassword( CustomerDTO customerDTO){

        Optional<Customer> customer = customerRepo.findCustomerByEmailAndPassword(customerDTO.getEmail(), customerDTO.getPassword());
        if(customer.isPresent()){
            return customer.get();
        }else{
            return null;
        }

    }

    public void save(Customer customer){

        customerRepo.save(customer);
    }


    public List<Cart> getCartList(String email){
        Customer customer = customerRepo.findCustomerByEmail(email).get();
        return customer.getCartList();
    }

    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

}
