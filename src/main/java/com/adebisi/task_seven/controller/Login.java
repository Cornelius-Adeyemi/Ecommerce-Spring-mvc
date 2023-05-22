package com.adebisi.task_seven.controller;


import com.adebisi.task_seven.DTO.CustomerDTO;
import com.adebisi.task_seven.model.Customer;
import com.adebisi.task_seven.repository.CustomerRepo;
import com.adebisi.task_seven.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {


    private CustomerService customerService;
    private CustomerRepo customerRepo;
    @Autowired
    public Login(CustomerService customerService, CustomerRepo customerRepo){
        this.customerService = customerService;
        this.customerRepo = customerRepo;
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String loginChecker = (String)session.getAttribute("identity");
        System.out.println("*****************login "+ loginChecker );
       if(loginChecker !=null ){
           System.out.println("***************** I'm inside" );
           return "redirect:/";
       }


        model.addAttribute("customer", new CustomerDTO());
        return  "sign-in";
    }
    @PostMapping("/login")
    public String loginPost(@ModelAttribute("customer") CustomerDTO customerDTO, HttpServletRequest request, Model model){

     Customer customer = customerService.findByEmailAndPassword(customerDTO);
     if(customer== null){
         model.addAttribute("error", "Incorrect login details");
         return "sign-in";
     }
     HttpSession session = request.getSession(true);
     session.setAttribute("identity", customerDTO.getEmail());
     return "redirect:/";
    }



    @GetMapping("/signup")
    public ModelAndView  signup(ModelAndView modelAndView){

        modelAndView.setViewName("sign-up");
        modelAndView.addObject("customer", new CustomerDTO());
        return  modelAndView;
    }

    @PostMapping("/signup")
    public String finishSignUp(@ModelAttribute("customer") CustomerDTO customerDTO, HttpServletRequest request, Model model){
         Customer customer = new Customer(customerDTO);
         boolean emailchecker = customerService.findbyEmail(customerDTO);
         if(emailchecker){
           model.addAttribute("error", "This email already exit please login");
           return "sign-up";
         }
         customerService.save(customer);

        HttpSession session = request.getSession(true);
         session.setAttribute("identity", customerDTO.getEmail());

        return "redirect:/";
    }
@GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("identity");
        return "redirect:/";
    }
}
