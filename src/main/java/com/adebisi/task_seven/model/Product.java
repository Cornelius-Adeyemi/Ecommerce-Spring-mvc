package com.adebisi.task_seven.model;


import com.adebisi.task_seven.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="product",uniqueConstraints = {@UniqueConstraint(name="product_name", columnNames="name")})
public class Product {
    @Id
    @SequenceGenerator(name="product_id", sequenceName = "product_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name="image", nullable=false)
    private byte[] image;

    public Product(ProductDTO productDTO){
        name = productDTO.getName();
        price = productDTO.getPrice();
        quantity = productDTO.getQuantity();
        category = productDTO.getCategory();
        image = productDTO.getImage();
    }


}
