package com.adebisi.task_seven.controller;


import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.model.Order;
import com.adebisi.task_seven.service.CartService;
import com.adebisi.task_seven.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {

    private CustomerService customerService;
    private CartService cartService;

    @Autowired
    public OrderController( CustomerService customerService, CartService cartService){
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping("/ordernow")
    public ModelAndView makeOrder(HttpServletRequest request){
        HttpSession session = request.getSession();
        String identity = (String)session.getAttribute("identity");

        Customer customer = customerService.returnCustomerByEmail(identity);
        List<Cart>  cartList = customer.getCartList();
      //  List<Order> orderList = new ArrayList<>();
        for (Cart cart: cartList){
            Order order = new Order(cart);
            order.setCustomer(customer);
           customer.getOrderList().add(order);

        }
        customerService.save(customer);
//        cartService.deleteAllByCustomer(customer);
        cartService.deleteAllById(customer.getId());

       // Customer customer1 = customerService.returnCustomerByEmail(identity);



        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("status", "Your order was successfully");
        modelAndView.setViewName("redirect:/carts");
        return  modelAndView;
    }
}
