package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.controller.dto.screening.ScreenDtoProjection;
import com.example.springbootmoviereservationsystem.domain.Screening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    Page<ScreenDtoProjection> findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(String title,
                                                                                          LocalDateTime whenScreened,
                                                                                          Pageable pageable);
}
