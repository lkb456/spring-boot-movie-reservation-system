package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
