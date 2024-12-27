package com.example.cinema.services;

import com.example.cinema.models.Film;
import com.example.cinema.models.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionService {
    public List<Session> getSessionsByFilm(Film film);
    public List<Session> getSessionByFilmAndDate(Film film, LocalDateTime date);
}
