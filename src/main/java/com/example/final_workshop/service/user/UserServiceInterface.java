package com.example.final_workshop.service.user;

import com.example.final_workshop.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getUsers();
    User addUser(User user);
}
