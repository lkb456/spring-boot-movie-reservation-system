package com.example.springbootmoviereservationsystem.domain.reservation;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @EntityGraph(value = "reserveWithAll",
            type = EntityGraph.EntityGraphType.FETCH)
    Optional<Reservation> findById(Long reservationId);

}
