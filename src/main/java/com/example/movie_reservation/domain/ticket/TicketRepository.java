package com.example.movie_reservation.domain.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}