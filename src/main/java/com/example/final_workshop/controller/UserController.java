package com.example.final_workshop.controller;

import com.example.final_workshop.entity.User;
import com.example.final_workshop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users2")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
