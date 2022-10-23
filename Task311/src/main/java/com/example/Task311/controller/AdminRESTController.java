package com.example.Task311.controller;

import com.example.Task311.model.User;
import com.example.Task311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminRESTController {

    private final UserService userService;


    @Autowired
    public AdminRESTController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(){
        List<User> userList = userService.getListUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Integer id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.addUser(user);
        return ResponseEntity.ok(user)  ;
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/users/name")
    public User showUserName(@AuthenticationPrincipal User user) {
        return user;
    }


}
