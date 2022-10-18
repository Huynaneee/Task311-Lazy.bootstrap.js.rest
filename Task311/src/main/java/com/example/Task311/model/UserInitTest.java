package com.example.Task311.model;

import com.example.Task311.service.RoleService;
import com.example.Task311.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Component
public class UserInitTest {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserInitTest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void generateUsersTest() {

        Role role1 = new Role(1L,"ROLE_USER");
        Role role2 = new Role(2L, "ROLE_ADMIN");

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User admin = new User(firstName, "Chernysow",33, "sda@yandex.ri", "101", Collections.singleton(role2));
        User user = new User(firstName, "Galustyan",22, "p2p@gmail.ci", "102", Collections.singleton(role1));
        User testUser = new User(firstName, "Moon",52,"moon21@sda", "100",roles);


        roleService.addRole(role1);
        roleService.addRole(role2);

        userService.addUser(admin);
        userService.addUser(user);
        userService.addUser(testUser);




    }
}
