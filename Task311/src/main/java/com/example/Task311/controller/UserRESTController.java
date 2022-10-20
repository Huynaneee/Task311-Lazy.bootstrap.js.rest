package com.example.Task311.controller;


import com.example.Task311.model.User;
import com.example.Task311.service.RoleService;
import com.example.Task311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRESTController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users/name")
    public User showUserName(@AuthenticationPrincipal User user) {
        return user;
    }
}
