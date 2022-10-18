package com.example.Task311.dao;

import com.example.Task311.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);

    List<Role> getAllRoles();

    Role getRoleBtId(int id);
}
