package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
