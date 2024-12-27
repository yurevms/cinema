package com.example.cinema.models;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;  // Код продажи, ПК, автовычисляемое поле

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false, referencedColumnName = "ticket_id", foreignKey = @ForeignKey(name = "fk_sale_ticket"))
    private Ticket ticket;  // Связь с таблицей Билеты

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_sale_user"))
    private User user;  // Связь с таблицей Клиенты

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false, referencedColumnName = "session_id", foreignKey = @ForeignKey(name = "fk_sale_session"))
    private Session session;  // Связь с таблицей Сеансы

    @Column(name = "sale_dateTime", nullable = false)
    private LocalDateTime saleDatetime;  // Дата и время продажи билета
}