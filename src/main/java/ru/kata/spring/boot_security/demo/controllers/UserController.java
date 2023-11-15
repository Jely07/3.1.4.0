package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String findAll(Model model, User user, Principal principal){
        List<User> users= userService.findAll();
        model.addAttribute("users",users);
        List<Role> roles =roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        User user1 = userService.findByUsername(principal.getName());
        model.addAttribute("user1",user1);
        return "admin";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user,Model model){
        List<Role> roles =roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return "redirect:/admin";
    }
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("user-update/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id")Long id,Model model){
        User user = userService.findById(id);
        List<Role> roles =roleRepository.findAll();
        model.addAttribute("user",user);
        model.addAttribute("roles",roles);
        return user;
    }
    @PostMapping ("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
