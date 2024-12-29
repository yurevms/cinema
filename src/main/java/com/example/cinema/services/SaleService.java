package com.example.cinema.services;

import com.example.cinema.DTO.SaleReportDTO;
import com.example.cinema.models.Sale;
import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {
    @Transactional
    public void save(Long userId, Long sessionId);
    public List<SaleReportDTO> getSalesReport(LocalDateTime startDate, LocalDateTime endDate);
}
