package com.example.cinema.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity // Аннотация, указывающая, что это сущность
@Table(name = "films") // Имя таблицы в базе данных
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическое увеличение ID
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "cover_image", nullable = true)
    private String cover_image;  // Путь к изображению
}

