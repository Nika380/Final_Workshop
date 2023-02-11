package com.example.final_workshop.controller;

import com.example.final_workshop.security.SecUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class check {
    @GetMapping
    public String check(@AuthenticationPrincipal SecUser user) {
        String name = user.getUsername();
        String perm = user.getAuthorities().toString();
        return name + " " + perm;
    }
}
