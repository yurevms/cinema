package com.example.cinema.repositories;

import com.example.cinema.models.Film;
import com.example.cinema.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByFilm(Film film);
    List<Session> findByFilmAndDate(Film film, LocalDateTime date);
}
