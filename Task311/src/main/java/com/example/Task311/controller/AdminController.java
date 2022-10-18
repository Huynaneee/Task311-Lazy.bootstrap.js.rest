package com.example.Task311.controller;

import com.example.Task311.model.Role;
import com.example.Task311.model.User;
import com.example.Task311.service.RoleService;
import com.example.Task311.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
@Log
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("logUser", user);
        model.addAttribute("users", userService.getListUsers());
        model.addAttribute("newUser",new User());
        model.addAttribute("updateUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }


    @PostMapping("/")
    public String createUser (@ModelAttribute("new User") User user,
                              @RequestParam(required = false,name = "roles[]") String [] ROLES) {
        Set<Role> roleSet = new HashSet<>();
        if (ROLES == null) {
            roleSet.add(roleService.getRoleBtId(2));
        } else  {
            for (String role: ROLES) {
                roleSet.add(roleService.getRoleBtId(Integer.parseInt(role)));
            }
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:";
    }


    @RequestMapping(method = RequestMethod.PUT)
    public String updateUser (@RequestParam(required = false, name = "firstNameEdit") String firstNameEdit,
                              @RequestParam(required = false, name = "lastNameEdit") String lastNameEdit,
                              @RequestParam(required = false, name = "ageEdit") int ageEdit,
                              @RequestParam(required = false, name = "emailEdit") String emailEdit,
                              @RequestParam(required = false, name = "passwordEdit") String passwordEdit,
                              @RequestParam(required = false, name = "roles[]") String[] ROLES,
                              @RequestParam(name = "idEdit") int idEdit) {
        User update = userService.getUserById(idEdit);
        update.setFirstName(firstNameEdit);
        update.setLastName(lastNameEdit);
        update.setAge(ageEdit);
        update.setEmail(emailEdit);
        update.setPassword(passwordEdit);
        Set<Role> roles1 = new HashSet<>();
        if (ROLES == null) {
            roles1.add(roleService.getRoleBtId(2));
        } else {
            for (String role : ROLES) {
                roles1.add(roleService.getRoleBtId(Integer.parseInt(role)));
            }
        }
        update.setRoles(roles1);
        return "redirect:";
    }

    @DeleteMapping("/admin")
    public String removeUser(@RequestParam(name = "idDelete") int idDelete) {
        userService.removeUser(idDelete);
        return "redirect:";
    }


}
