package com.example.movie_reservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
