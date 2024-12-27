package com.example.cinema.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int id;

    //связь с фильмом
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, referencedColumnName = "film_id", foreignKey = @ForeignKey(name = "fk_session_film"))
    private Film film;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "ticket_price", nullable = false)
    private Integer ticketPrice;

    private String formattedDate;

    // Связь с залом
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, referencedColumnName = "room_id", foreignKey = @ForeignKey(name = "fk_session_room"))
    private Room room;

    // Связь с сотрудником
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false, referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "fk_session_staff"))
    private Staff staff;
}