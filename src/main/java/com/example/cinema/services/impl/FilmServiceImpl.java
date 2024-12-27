package com.example.cinema.services.impl;

import com.example.cinema.file.FileStorageService;
import com.example.cinema.models.Film;
import com.example.cinema.repositories.FilmRepository;
import com.example.cinema.services.FilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Film save(Film film, MultipartFile photo) throws IOException {
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = fileStorageService.saveFile(photo);
            film.setCover_image(photoUrl);
        }
        return filmRepository.save(film);
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }
}
