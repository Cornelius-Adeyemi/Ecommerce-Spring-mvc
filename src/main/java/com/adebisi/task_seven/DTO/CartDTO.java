package com.adebisi.task_seven.DTO;


import com.adebisi.task_seven.model.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {

  private String name;

  private int price;
  private int quantity;

  private  Long product_id;


  public CartDTO(Cart cart){
    name= cart.getName();
    price = cart.getPrice();
    quantity = cart.getQuantity();


  }


}
