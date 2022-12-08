package com.example.springbootmoviereservationsystem.domain.repository;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.Screening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("select new " +
            "com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto.MovieDto" +
            "(s.movie.title," +
            " s.movie.fee," +
            " s.movie.runningTime," +
            " s.movie.releaseStatus) " +
            "from Screening s " +
            "where s.whenScreened <= :startTime " +
            "or s.movie.title like %:title% " +
            "order by s.whenScreened asc")
    Page<MovieResponseDto.MovieDto> findScreeningStartTimeAfterAndTitle(@Param("title") String title,
                                                               @Param("startTime") LocalDateTime startTime,
                                                               Pageable pageable);
}
