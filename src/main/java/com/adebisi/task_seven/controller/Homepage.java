package com.adebisi.task_seven.controller;

import com.adebisi.task_seven.model.Cart;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.service.CustomerService;
import com.adebisi.task_seven.service_interface.ProductServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Homepage {

    ProductServiceInterface productServiceInterface;
    CustomerService customerService;

    @Autowired
    public Homepage(ProductServiceInterface productServiceInterface, CustomerService customerService){
        this.productServiceInterface = productServiceInterface;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String homepage(HttpServletRequest request, Model model ){
        HttpSession session = request.getSession();
        String loginStatus = (String)session.getAttribute("identity");
        String pageNo = request.getParameter("pageNo");
        int defaultPage = 1;
        int pageSize = 6;
        if(loginStatus !=null){
            model.addAttribute("login", true);
            List<Cart> cartList = customerService.getCartList(loginStatus);
            model.addAttribute("cartsize", cartList.size());
        }else{
            model.addAttribute("login", false);
        }

        if( pageNo != null){
            defaultPage =  Integer.parseInt( pageNo);
        }
        Page<Product>  productPage = productServiceInterface.findAllPaginatedProduct(defaultPage, pageSize);
        List<Product> products = productPage.getContent();
        model.addAttribute("currentPage", defaultPage);
        model.addAttribute("totalPage",productPage.getTotalPages() );
        model.addAttribute("totalItem", productPage.getTotalElements());

       // List<Product> products = productServiceInterface.findall();

        model.addAttribute("products", products);
        return "index2";
    }


    @GetMapping("/paging/{id}")
    public String page(@PathVariable("id") int id){
        return "redirect:/?pageNo=" + id;


    }
}
