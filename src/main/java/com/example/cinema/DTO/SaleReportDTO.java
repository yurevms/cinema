package com.example.cinema.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class SaleReportDTO {

    private LocalDateTime saleDate;
    private String filmTitle;
    private Long userId;
    private java.sql.Timestamp reportDate;
    private Integer ticketPrice;

    public SaleReportDTO(LocalDateTime saleDate, String filmTitle, Long userId, java.sql.Timestamp reportDate, Integer ticketPrice) {
        this.saleDate = saleDate;
        this.filmTitle = filmTitle;
        this.userId = userId;
        this.reportDate = reportDate;
        this.ticketPrice = ticketPrice;
    }

}