package com.example.cinema.services;

import com.example.cinema.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    public UserDetails loadUserByUsername(String username);
    public void saveUser(User user);

}
