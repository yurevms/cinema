package com.example.cinema.models;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "status", nullable = false)
    private String status;

    // Связь с Сеансом (много билетов могут быть на один сеанс)
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false, referencedColumnName = "session_id", foreignKey = @ForeignKey(name = "fk_ticket_session"))
    private Session session;
}
