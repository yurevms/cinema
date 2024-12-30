package com.example.cinema.repositories;

import com.example.cinema.models.Staff;
import com.example.cinema.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
}
