package com.example.cinema.contollers;


import com.example.cinema.DTO.SaleReportDTO;
import com.example.cinema.services.SaleService;
import com.example.cinema.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ReportController {

    private SaleService saleService;

    private UserService userService; // Для получения данных об администраторе

    // Отображение страницы отчёта
    @GetMapping("/reportForm")
    public String showReportForm() {
        return "reportForm";
    }

    // Обработка формы с периодом
    @PostMapping("/reportForm")
    @ResponseBody
    public ResponseEntity<?> generateReport(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {

        // Получение отчёта по продажам за период
        List<SaleReportDTO> sales = saleService.getSalesReport(startDate, endDate);

        // Получение информации о текущем администраторе
        String adminName = "Тестовый Администратор";

        // Подсчёт общей выручки
        Integer summ = 0;
        for (SaleReportDTO sale: sales){
            summ += sale.getTicketPrice();
        }

        LocalDateTime now = LocalDateTime.now();

        // Формируем и возвращаем ответ в формате JSON
        Map<String, Object> response = new HashMap<>();
        response.put("sales", sales);
        response.put("totalRevenue", summ);
        response.put("adminName", adminName);
        response.put("nowTime", now);

        return ResponseEntity.ok(response);
    }
}
