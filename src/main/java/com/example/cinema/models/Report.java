package com.example.cinema.models;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "report")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false, referencedColumnName = "sale_id", foreignKey = @ForeignKey(name = "fk_report_sale"))
    private Sale sale; // Ссылка на проданный билет (сделанную продажу)

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false, referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "fk_report_staff"))
    private Staff staff; // Ссылка на сотрудника, который сформировал отчет

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate; // Дата отчета

    @Column(name = "revenue", nullable = false)
    private Integer revenue; // Выручка за день
}
