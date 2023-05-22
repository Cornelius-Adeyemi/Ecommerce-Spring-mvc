package com.adebisi.task_seven.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Wish {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    private Long product_id;

    private int price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Wish(Product product){
        name = product.getName();
        product_id = product.getId();
        price = product.getPrice();
    }


}
