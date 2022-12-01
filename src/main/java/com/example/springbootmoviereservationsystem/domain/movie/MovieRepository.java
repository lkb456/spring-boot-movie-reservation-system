package com.example.springbootmoviereservationsystem.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
