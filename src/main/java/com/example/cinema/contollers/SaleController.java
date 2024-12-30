package com.example.cinema.contollers;

import com.example.cinema.models.Session;
import com.example.cinema.models.User;
import com.example.cinema.services.SaleService;
import com.example.cinema.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
@RequestMapping("/payment")
public class SaleController {
    private final SessionService sessionService;
    private final SaleService saleService;

    // Отображение страницы оплаты
    @GetMapping("/{sessionId}")
    public String showPaymentPage(@PathVariable Long sessionId, Model model) {
        Session session = sessionService.getSessionById(sessionId);

        // Форматируем дату
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = session.getDate().format(formatter);

        // Получаем текущего пользователя из SecurityContext и извлекаем ID
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("sessionItem", session);
        model.addAttribute("film", session.getFilm());
        model.addAttribute("userId", userId); // Передаем ID пользователя

        return "payment";
    }

    // Обработка оплаты
    @PostMapping("/complete")
    public String processPayment(@RequestParam Long userId, @RequestParam Long sessionId) {
        // Сохраняем данные о платеже в базе данных
        saleService.save(userId, sessionId);
        return "redirect:/films";
    }
}
