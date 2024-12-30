package com.example.cinema.services.impl;

import com.example.cinema.models.Staff;
import com.example.cinema.repositories.StaffRepository;
import com.example.cinema.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.example.cinema.models.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // Поиск пользователя
        User user = userRepository.findByLogin(login).orElse(null);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        }

        Staff staff = staffRepository.findByUsername(login).orElse(null);
        if (staff != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(staff.getUsername())
                    .password(staff.getPassword())
                    .roles(staff.getRole().name())
                    .build();
        }

        throw new UsernameNotFoundException("User or Staff not found: " + login);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public void saveStaff(Staff staff) { staffRepository.save(staff);}

}
