package com.example.cinema.services.impl;

import com.example.cinema.DTO.SaleReportDTO;
import com.example.cinema.models.Sale;
import com.example.cinema.models.Session;
import com.example.cinema.models.User;
import com.example.cinema.repositories.ReportRepository;
import com.example.cinema.repositories.SaleRepository;
import com.example.cinema.repositories.SessionRepository;
import com.example.cinema.repositories.UserRepository;
import com.example.cinema.services.SaleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final ReportRepository reportRepository;

    @Override
    public void save(Long userId, Long sessionId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Сеанс не найден"));

        Sale sale = new Sale();
        sale.setUser(user);
        sale.setSession(session);
        sale.setSaleDatetime(LocalDateTime.now());

        saleRepository.save(sale);
    }

    @Override
    public List<SaleReportDTO> getSalesReport(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findSalesByDateRange(startDate, endDate);
    }
}
