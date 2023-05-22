package com.adebisi.task_seven.model;


import com.adebisi.task_seven.DTO.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint( columnNames="email", name="email_unique")})
public class Customer {

    @Id
    @SequenceGenerator(name="customer_id", sequenceName = "customer_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false )
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Wish> wishLists = new ArrayList<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    public Customer(CustomerDTO customerDTO){
        this.name = customerDTO.getName();
        this.email = customerDTO.getEmail();
        this.password = customerDTO.getPassword();
    }
}
