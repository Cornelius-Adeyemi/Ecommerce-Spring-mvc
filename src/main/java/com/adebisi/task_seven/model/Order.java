package com.adebisi.task_seven.model;

import com.adebisi.task_seven.enumPackage.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private Long productId;

    private  int quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status = Status.MAKE_ORDER;
@CreationTimestamp
    private LocalDateTime dateCreated;


public Order(Cart cart){
    name = cart.getName();
    price = cart.getTotal_price();
    quantity = cart.getQuantity();
    status = Status.MAKE_ORDER;
    productId = cart.getProduct_id();
}

}
