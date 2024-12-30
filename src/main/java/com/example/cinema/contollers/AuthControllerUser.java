package com.example.cinema.contollers;

import com.example.cinema.models.Enum.Role;
import com.example.cinema.models.User;
import com.example.cinema.services.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthControllerUser {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/registration")
    public String showRegistrationOptions() {
        return "registration-options"; // Страница с выбором типа регистрации
    }



    @GetMapping("/registration/user")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-user"; // Отправляем форму регистрации
    }

    @PostMapping("/registration/user")
    public String registerUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.USER); // Назначаем роль USER по умолчанию
        }
        userDetailsService.saveUser(user);
        return "redirect:/login"; // Перенаправляем на страницу входа после регистрации
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный логин или пароль");
        }
        model.addAttribute("user", new User());
        return "login"; // Отправляем на страницу входа
    }

    @GetMapping("/login/error")
    public String showErrorPage(Model model) {
        model.addAttribute("error", "Неверный логин или пароль");
        return "error";
    }
}
