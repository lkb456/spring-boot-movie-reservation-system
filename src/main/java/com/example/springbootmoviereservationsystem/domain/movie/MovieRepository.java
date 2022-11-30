package com.example.springbootmoviereservationsystem.domain.movie;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
