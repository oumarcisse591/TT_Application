package com.slabtech.ttapplication.Dao;

import com.slabtech.ttapplication.Entity.Role;

public interface RoleDao {
    public Role findRoleByName(String theRoleName);
}
