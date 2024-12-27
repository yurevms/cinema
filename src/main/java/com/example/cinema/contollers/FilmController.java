package com.example.cinema.contollers;

import com.example.cinema.models.Film;
import com.example.cinema.models.Session;
import com.example.cinema.repositories.FilmRepository;
import com.example.cinema.services.FilmService;
import com.example.cinema.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final SessionService sessionService;

    @RequestMapping(
            value = "/films",
            method = RequestMethod.GET
    )
    public String getAllFilm(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        //model.addAttribute("sessions", sessionService.getAllSessions());
        return "films";
    }

    //ДОБАЛЕНИЕ ФИЛЬМА
    @GetMapping("/films/add")
    public String showAddFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "addFilm";  // Название страницы для добавления фильма
    }

    @PostMapping("/films/add")
    public String addFilm(@ModelAttribute Film film, @RequestParam("photo") MultipartFile photo) {
        try {
            filmService.save(film, photo);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/films";
    }

    //УДАЛЕНИЕ ФИЛЬМА
    @PostMapping("/films/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        filmService.deleteFilmById(id);
        return "redirect:/films";
    }

}

