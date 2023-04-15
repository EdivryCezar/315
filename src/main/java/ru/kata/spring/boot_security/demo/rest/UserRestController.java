package ru.kata.spring.boot_security.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;

@RestController

public class UserRestController {
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("index");
    }

    @GetMapping("/user")
    public ModelAndView showAdminPage() {
        return new ModelAndView("user");
    }

    @GetMapping("/user/authUser")
    public ResponseEntity<User> getAuthUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
