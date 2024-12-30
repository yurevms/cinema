package com.example.cinema.contollers;

import com.example.cinema.models.Enum.Role;
import com.example.cinema.models.Staff;
import com.example.cinema.services.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthControllerStaff {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping("/registration/staff")
    public String showStaffRegistrationForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "registration-staff"; // Страница регистрации сотрудника
    }

    @PostMapping("/registration/staff")
    public String registerStaff(@ModelAttribute Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        if (staff.getRole() == null) {
            staff.setRole(Role.USER);
        }
        userDetailsService.saveStaff(staff);
        return "redirect:/login";
    }
}

