package com.example.cinema.services;

import com.example.cinema.models.Film;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilmService {

    @Transactional
    Film save(Film film, MultipartFile photo) throws IOException;

    Film getFilmById(Long id);
    List<Film> getAllFilms();
    void deleteFilmById(Long id);
}
