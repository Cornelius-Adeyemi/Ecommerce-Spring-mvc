package com.adebisi.task_seven.controller.admin;

import com.adebisi.task_seven.DTO.OrderDTO;
import com.adebisi.task_seven.DTO.ProductDTO;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.model.Order;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.repository.ProductRepo;
import com.adebisi.task_seven.service.AdminService;
import com.adebisi.task_seven.service.CustomerService;
import com.adebisi.task_seven.service.ProductService;
import com.adebisi.task_seven.service_interface.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private AdminService adminService;
    private ProductService productService;
    private ProductRepo productRepo;

    private OrderService orderService;

    private CustomerService customerService;

    @Autowired
    public AdminController(AdminService adminService, ProductService productService ,OrderService orderService,
                           CustomerService customerService){
        this.adminService = adminService;
        this.productService = productService;
       this.orderService = orderService;
       this.customerService = customerService;
    }



    @GetMapping("/page")
    public String homepage(Model model, HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        boolean loginChecker = false;
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("admin")){
                System.out.println("************ "+ cookie.getValue());
                loginChecker = true;
            }
        }

        if(!loginChecker){

            return "redirect:/admin/login";
        }

        String add= request.getParameter("add");
        if(add !=null){
            model.addAttribute("addproduct", new ProductDTO());
            String errorAdd = request.getParameter("errorAdd");
            model.addAttribute("errorAdd", errorAdd);
        }
        String edit= request.getParameter("edit");
        if(edit !=null){
            String stringId = request.getParameter("id");
            Long id  = Long.valueOf(stringId);
            Product product = productService.findProductById(id);
            ProductDTO productDTO = new ProductDTO(product);
            model.addAttribute("editproduct", productDTO);

        }





        List<Product> listOfProduct = productService.findall();
        List<Order>  orderList =  orderService.findAllOder();
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("orderDTO", new OrderDTO());
        model.addAttribute("customers",customerList);
         model.addAttribute("orders", orderList);
        model.addAttribute("size", listOfProduct.size());
        model.addAttribute("products", listOfProduct);
        model.addAttribute("add", add);
        model.addAttribute("edit", edit);
        return "admin";

    }



    @PostMapping("/addproduct")
    public String addproduct(@ModelAttribute("product") ProductDTO productDTO, BindingResult result,
                             @RequestParam("image")MultipartFile image, Model model)  {

      if(!image.getContentType().startsWith("image/")){

          return "redirect:/admin/page?add=true&errorAdd=invalid file type";
      }
        byte[] data;
        try {
           data  = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productDTO.setImage(data);

     boolean checker = productService.findByProductName(productDTO);

     if(!checker){

         return "redirect:/admin/page?add=true&errorAdd=product already exit";
     }

      productService.saveProduct(productDTO);


     return "redirect:/admin/page";

    }



    @GetMapping("/addproduct")
    public String addProductDirect(){

        return "redirect:/admin/page?add=true";
    }

    @GetMapping("/editproduct")
    public String editProdut(@RequestParam("id") Long id, HttpServletRequest request ){

        return "redirect:/admin/page?edit=true&id="+id;
    }
@PostMapping("/editproduct")
    public String postEditProduct(@ModelAttribute("editproduct") ProductDTO productDTO){

       Product product =  productService.findProductById(productDTO.getId());
       product.setName(productDTO.getName());
       product.setPrice(productDTO.getPrice());
       product.setQuantity(productDTO.getQuantity());
       productService.saveProduct(product);
       return "redirect:/admin/page";
    }

@GetMapping("/delete")
   public String deleteProduct(@RequestParam("id") Long id){
        productService.deleteProduct(id);

        return "redirect:/admin/page";
   }

@GetMapping("/image/{id}")
   public ResponseEntity<byte[]> getImaege(@PathVariable("id") long id){
        Product product = productService.findProductById(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    return new ResponseEntity<>(product.getImage(),headers, HttpStatus.OK);

   }
@GetMapping("/deleteorder")
    public String deleteOrder(@RequestParam("id") Long id){
        orderService.deleteOrder(id);

        return "redirect:/admin/page";
}

@PostMapping("/changeorder")
    public String changeOrderStatus(@ModelAttribute("orderDTO") OrderDTO orderDTO){
    orderService.changeOrderStatus(orderDTO);

    return "redirect:/admin/page";
}


}
