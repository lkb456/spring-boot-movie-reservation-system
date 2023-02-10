package com.example.movie_reservation.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByReleaseDateBetweenAndTitleStartingWith(LocalDate firstDate, LocalDate secondDate, String title);
}
