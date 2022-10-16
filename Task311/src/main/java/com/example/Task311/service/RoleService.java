package com.example.Task311.service;

import com.example.Task311.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);
    public List<Role> getAllRoles();

}
