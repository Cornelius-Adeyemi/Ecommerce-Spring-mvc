package com.adebisi.task_seven.controller.admin;


import com.adebisi.task_seven.DTO.AdminDTO;
import com.adebisi.task_seven.model.Admin;
import com.adebisi.task_seven.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    private AdminService adminService;

    @Autowired
    public AdminLoginController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request){
        boolean checker = false;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("admin")){
                checker= true;
            }
        }
        if(checker){
            return "redirect:/admin/page";
        }
        model.addAttribute("admin", new AdminDTO());
        return "adminlogin";
    }

    @PostMapping("/login")
    public String loggedIn(@ModelAttribute("admin") AdminDTO adminDTO, Model model, HttpServletResponse response){
         boolean admin = false;

        String email= "aadebisi11@yahoo.com";
        String password = "yetunde";
        if(email.equals(adminDTO.getEmail()) && password.equals(adminDTO.getPassword())){
            admin=true;
        }

        if(!admin){
            model.addAttribute("error", "invalid login details");
            return "adminlogin";

        }

        Cookie cookie = new Cookie("admin", adminDTO.getEmail());
        cookie.setMaxAge(-180);
        response.addCookie(cookie);
        return "redirect:/admin/page";
    }


    @GetMapping("/logout")
    public String logout(Model model, HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("admin")){
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }

        return "redirect:/";
    }
}
