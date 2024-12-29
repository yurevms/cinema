package com.example.cinema.repositories;

import com.example.cinema.DTO.SaleReportDTO;
import com.example.cinema.models.Report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    @Query("SELECT new com.example.cinema.DTO.SaleReportDTO(s.saleDatetime, f.title, s.user.id, CURRENT_TIMESTAMP, ses.ticketPrice) " +
            "FROM Sale s " +
            "JOIN s.session ses " +
            "JOIN ses.film f " +
            "WHERE s.saleDatetime BETWEEN :startDate AND :endDate")
    List<SaleReportDTO> findSalesByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
