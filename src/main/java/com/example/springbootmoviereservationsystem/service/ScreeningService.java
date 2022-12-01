package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.response.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;

    @Transactional
    public ScreeningSaveResponseDto saveScreen(Long movieId, LocalDateTime whenScreened) {
        Movie movie = movieFind(movieId);
        Screening savedScreening = screenSave(whenScreened, movie);
        return ScreeningSaveResponseDto.of(savedScreening);

    }

    private Screening screenSave(LocalDateTime whenScreened, Movie movie) {
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(whenScreened)
                .build());
    }

    private Movie movieFind(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }
}
