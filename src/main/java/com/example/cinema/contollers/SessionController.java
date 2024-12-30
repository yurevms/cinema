package com.example.cinema.contollers;


import com.example.cinema.models.Film;
import com.example.cinema.models.Room;
import com.example.cinema.models.Session;
import com.example.cinema.models.Staff;
import com.example.cinema.services.FilmService;
import com.example.cinema.services.RoomService;
import com.example.cinema.services.SessionService;
import com.example.cinema.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final FilmService filmService;
    private final RoomService roomService;
    private final StaffService staffService;

    @GetMapping("/{id}")
    public String getFilmSessions(@PathVariable Long id, Model model, Authentication authentication) {

        boolean authenticated = (authentication != null && authentication.isAuthenticated());
        model.addAttribute("authenticated", authenticated);
        String role = "USER"; // По умолчанию роль пользователя
        if (authentication != null) {
            role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining());
        }

        Film film = filmService.getFilmById(id);
        List<Session> sessions = sessionService.getSessionsByFilm(film);

        // Преобразование даты в строку для каждого сеанса
        Map<String, List<Session>> sessionsByDate = new LinkedHashMap<>();
        for (Session session : sessions) {
            String formattedDate = session.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            session.setFormattedDate(formattedDate);

            // Группируем сессии по дате
            sessionsByDate.computeIfAbsent(formattedDate, k -> new ArrayList<>()).add(session);
        }

        model.addAttribute("role", role);
        model.addAttribute("film", film);
        model.addAttribute("sessionsByDate", sessionsByDate);
        model.addAttribute("filmId", id);

        return "sessions"; // Переходим к HTML шаблону с сеансами
    }

    // Страница добавления сессии
    @GetMapping("/add")
    public String showAddSessionForm(Model model) {
        // Создаем пустую сессию для заполнения
        model.addAttribute("session", new Session());
        // Отправляем на страницу добавления сессии
        return "addSession";
    }

    // Обработка POST-запроса для добавления сессии
    @PostMapping("/add")
    public String addSession(@ModelAttribute Session session,@RequestParam Long filmID, @RequestParam Long room, @RequestParam Long staff, Model model) {
        Film currentFilm = filmService.getFilmById(filmID);
        model.addAttribute("filmId", filmID);

        // Получаем зал и сотрудника по их идентификаторам
        Room selectedRoom = roomService.getRoomById(room);
        Staff selectedStaff = staffService.getStaffById(staff);

        // Устанавливаем зал и сотрудника в сессию
        session.setRoom(selectedRoom);
        session.setStaff(selectedStaff);
        session.setFilm(currentFilm);

        // Сохраняем сессию
        sessionService.saveSession(session);

        // После успешного добавления, можно отправить пользователя на страницу фильма
        return "redirect:/session/" + filmID;
    }

    //УДАЛЕНЕИ СЕССИИ
    @PostMapping("/{filmId}/{sessionId}/delete")
    public String deleteSession(@PathVariable Long filmId,@PathVariable Long sessionId, Model model) {
        sessionService.deleteSession(sessionId);
        return "redirect:/session/" + filmId;
    }



}

