package com.example.cinema.services.impl;

import com.example.cinema.models.Report;
import com.example.cinema.repositories.ReportRepository;
import com.example.cinema.services.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    @Override
    public void saveReport(Report report) {
        reportRepository.save(report);
    }
}
