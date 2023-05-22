package com.adebisi.task_seven.controller;


import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.service.CartService;
import com.adebisi.task_seven.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class CartController {

    CustomerService customerService;
    CartService cartService;
    @Autowired
    public CartController(CustomerService customerService, CartService cartService){
        this.customerService= customerService;
        this.cartService = cartService;
    }

@GetMapping("/carts")
    public String cartPage(@ModelAttribute("status") String status, HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        if(identity ==null){
            return "redirect:/";
        }
        Customer customer = customerService.returnCustomerByEmail(identity);
        List<Cart> cartList = customer.getCartList();
        int totalCost = 0;

        for(Cart cart: cartList){
            totalCost += cart.getTotal_price();
        }
        if(status.equalsIgnoreCase("")) {
            model.addAttribute("status", false);
        }else{
            model.addAttribute("status", true);
        }

        model.addAttribute("totalcost", totalCost);
        model.addAttribute("cartlist", cartList);
        model.addAttribute("cartsize", cartList.size());
        return "cart";
    }

    @GetMapping("/carts/delete")
    public String deleteACartProduct(@RequestParam("id") Long id){
        cartService.deleteProductInCart(id);

        return "redirect:/carts";
    }
}
