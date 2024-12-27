package com.example.cinema.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity; // Вместимость зала

    @Column(name = "VIP_Seats", nullable = false)
    private Integer VIPSeats; // Количество мест повышенного комфорта
}
