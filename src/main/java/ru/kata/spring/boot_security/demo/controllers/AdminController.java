package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private List<User> users;

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;

    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /*@GetMapping()
    public String admin(Principal principal, ModelMap model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "admin";
    }*/
    @GetMapping
    public String listUsers(ModelMap model, Principal principal, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("admin" , userService.getUserByUsername(principal.getName()));
        model.addAttribute("newUser" ,new User());
        model.addAttribute("roles" , roleService.getAllRoles());
        return "admin";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }
    /*
    //вывод
    @GetMapping//(value = "")
    public String printAllUsers(ModelMap model, Principal principal, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("admin" , userService.getUserByUsername(principal.getName()));
        model.addAttribute("newUser" ,new User());
        model.addAttribute("roles" , roleService.getAllRoles());
        return "admin";
    }
    //сохранение
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }*/
    //удаление
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    /*
    //сохранение изменений
    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user){
        userService.editUser(user);
        return "redirect:/admin";
    }*/
}
