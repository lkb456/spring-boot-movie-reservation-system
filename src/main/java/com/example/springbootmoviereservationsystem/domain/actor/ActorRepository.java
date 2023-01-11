package com.example.springbootmoviereservationsystem.domain.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
