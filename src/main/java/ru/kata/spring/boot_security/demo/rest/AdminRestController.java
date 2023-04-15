package ru.kata.spring.boot_security.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserServiceImp userService;

    public AdminRestController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ModelAndView showAdminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable ("id") Long id) {
        System.out.println(user);
        userService.editUser(user, id);
        return ResponseEntity.ok(user);
    }

}
