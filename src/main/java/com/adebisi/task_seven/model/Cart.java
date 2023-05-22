package com.adebisi.task_seven.model;

import com.adebisi.task_seven.DTO.CartDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int total_price;

    private int quantity =1;

    private Long product_id;


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    public Cart(CartDTO cartDTO){

        name = cartDTO.getName();
        price = cartDTO.getPrice();
        total_price = cartDTO.getPrice();
        product_id = cartDTO.getProduct_id();

    }

    public Cart(Product product){

        name= product.getName();
        price = product.getPrice();
        product_id = product.getId();
        total_price = product.getPrice();
    }


}

