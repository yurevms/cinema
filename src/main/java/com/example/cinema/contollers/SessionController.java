package com.example.cinema.contollers;


import com.example.cinema.models.Film;
import com.example.cinema.models.Session;
import com.example.cinema.services.FilmService;
import com.example.cinema.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final FilmService filmService;
    LocalDateTime minDate;//дата первого сеанса

    @GetMapping("/{id}")
    public String getFilmSessions(@PathVariable Long id, Model model) {
        Film film = filmService.getFilmById(id);
        List<Session> sessions = sessionService.getSessionsByFilm(film);
        for (Session session : sessions) {
            minDate= session.getDate();
            break;
        }
        // Преобразование даты в строку для каждого сеанса
        for (Session session : sessions) {
            if(session.getDate().isBefore(minDate)) {
                minDate = session.getDate();
            }
            String formattedDate = session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            session.setFormattedDate(formattedDate);
        }
        List<Session> trueSession = sessionService.getSessionByFilmAndDate(film, minDate);

        model.addAttribute("film", film);
        model.addAttribute("trueSession", trueSession);
        model.addAttribute("sessions", sessions);

        return "sessions"; // Переходим к HTML шаблону с сеансами
    }


}

