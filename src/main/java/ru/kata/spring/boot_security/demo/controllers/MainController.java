package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("title","Форма входа");
        return "login";
    }
    @GetMapping("/logout")
    public String exit(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/user")
    public String infoUser(Principal principal,Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "user";
    }

}
