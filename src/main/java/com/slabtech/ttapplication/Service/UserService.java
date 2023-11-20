package com.slabtech.ttapplication.Service;

import com.slabtech.ttapplication.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    public User fingByUserName(String userName);
}
