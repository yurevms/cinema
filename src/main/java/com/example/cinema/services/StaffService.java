package com.example.cinema.services;

import com.example.cinema.models.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> findAll();
    Staff getStaffById(Long id);
}
