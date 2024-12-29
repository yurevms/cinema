package com.example.cinema.services.impl;

import com.example.cinema.models.Film;
import com.example.cinema.models.Session;
import com.example.cinema.repositories.SessionRepository;
import com.example.cinema.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public List<Session> getSessionsByFilm(Film film) {
        return sessionRepository.findByFilm(film);
    }

    @Override
    public List<Session> getSessionByFilmAndDate(Film film, LocalDateTime date) {
        return sessionRepository.findByFilmAndDate(film, date);
    }

    @Override
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}
