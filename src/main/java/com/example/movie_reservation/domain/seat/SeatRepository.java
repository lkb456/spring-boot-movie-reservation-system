package com.example.movie_reservation.domain.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface SeatRepository extends JpaRepository<Seat, Long>{

    Optional<Seat> findBySeatNumber(Integer seatNumber);
}
