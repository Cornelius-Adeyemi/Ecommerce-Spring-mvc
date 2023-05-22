package com.adebisi.task_seven.DTO;

import com.adebisi.task_seven.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private int price;

    private int quantity;

    private String category;

    private byte[] image;

    public ProductDTO(Product product){
        id = product.getId();
        name = product.getName();
        price =product.getPrice();
        quantity = product.getQuantity();
        category = product.getCategory();

    }

}
