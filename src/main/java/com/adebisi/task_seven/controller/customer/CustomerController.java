package com.adebisi.task_seven.controller.customer;


import com.adebisi.task_seven.DTO.CartDTO;
import com.adebisi.task_seven.DTO.CustomerDTO;
import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.model.Wish;
import com.adebisi.task_seven.repository.CartRepo;
import com.adebisi.task_seven.repository.CustomerRepo;
import com.adebisi.task_seven.service.CartService;
import com.adebisi.task_seven.service.CustomerService;
import com.adebisi.task_seven.service.ProductService;
import com.adebisi.task_seven.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    ProductService productService ;
    CustomerService customerService;
    CartService cartService;
    CustomerRepo customerRepo;
    WishListService wishListService;


    @Autowired
    public CustomerController( ProductService productService, CustomerService customerService,
                               CustomerRepo customerRepo, CartService cartService, WishListService wishListService){
        this.productService = productService;
        this.customerService = customerService;
        this.customerRepo = customerRepo;

        this.cartService= cartService;
        this.wishListService = wishListService;
    }

    @GetMapping("/addtocart")
    public String addToCart(@RequestParam("id") Long id, HttpServletRequest request){
        System.out.println("0000000000000000000000  id" + id);
        HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        if(identity !=null && id !=null){
            Product product = productService.findProductById(id);
            Customer customer =   customerRepo.findCustomerByEmail(identity).get();
            Optional<Cart> optionalCart = cartService.findCartByNameAndCustomer_Email(product.getName(), customer.getEmail());
            if(optionalCart.isPresent()){
                Cart cart = optionalCart.get();
                int quantity = cart.getQuantity()+1;
                int price = cart.getPrice() * quantity;
                cart.setTotal_price(price);
                cart.setQuantity(quantity);
                cartService.save(cart);
            }else{
                Cart cart = new Cart(product);
                cart.setCustomer(customer);
                customer.getCartList().add(cart);
                customerService.save(customer);

            }

        }

        return "redirect:/";
    }

@GetMapping("/addwish")
    public String addtowish(@RequestParam("id") Long id, HttpServletRequest request){
       HttpSession session = request.getSession();
        String identity = (String) session.getAttribute("identity");
        if(identity !=null && id !=null){
            Product product = productService.findProductById(id);
            Customer customer =   customerRepo.findCustomerByEmail(identity).get();
            Optional<Wish> optionalWish = wishListService.findByNameAndCustomer_Email(product.getName(), customer.getEmail());
            if(!optionalWish.isPresent()){
               Wish wish = new Wish(product);
               wish.setCustomer(customer);
               customer.getWishLists().add(wish);
               customerService.save(customer);

            }

        }

        return "redirect:/";

    }






//    @GetMapping("/")
//    public String test(){
//
//
//         Cart cart = cartService.findCartByNameAndCustomer_Email("cake","aabbii@gmail.com").get();
//         int quantity  = cart.getQuantity()+1;
//         cart.setQuantity(quantity);
//         cartService.save(cart);
////
////         CartDTO cartDTO = new CartDTO(cart);
////        System.out.println("********** "+ cartDTO);
//
//        return "redirect:/";
//    }


}
