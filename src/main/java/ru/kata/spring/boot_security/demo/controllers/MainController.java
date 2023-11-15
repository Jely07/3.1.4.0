package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;
    public MainController(UserService userService) {
        this.userService = userService;
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
