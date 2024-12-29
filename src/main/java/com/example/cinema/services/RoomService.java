package com.example.cinema.services;


import com.example.cinema.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> findAll();
    Room getRoomById(Long id);
}
