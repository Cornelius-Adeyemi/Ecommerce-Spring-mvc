package com.adebisi.task_seven.controller;


import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.model.Wish;
import com.adebisi.task_seven.service.CustomerService;
import com.adebisi.task_seven.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WishController {

    CustomerService customerService;
    WishListService wishListService;

    public WishController( CustomerService customerService, WishListService wishListService){
        this.customerService =customerService;
        this.wishListService = wishListService;
    }

    @GetMapping("/wishlist")
    public String wishpage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        if(identity ==null){
            return "redirect:/";
        }
        Customer customer = customerService.returnCustomerByEmail(identity);
        List<Wish> wishList  = customer.getWishLists();
        List<Cart> carts = customer.getCartList();

        model.addAttribute("cartNo", carts.size());
        model.addAttribute("wishes", wishList);
        model.addAttribute("size", wishList.size());
        return "wishlist";
    }


    @GetMapping("/wish/delete")
    public String deleteWish(@RequestParam("id") Long id){
        wishListService.deleteAWishById(id);
        return "redirect:/wishlist";
    }
}
