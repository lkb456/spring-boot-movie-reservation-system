package com.example.movie_reservation.domain.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
