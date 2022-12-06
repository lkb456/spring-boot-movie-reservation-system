package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;

    public Screening saveScreen(Long movieId, LocalDateTime whenScreened) {
        Movie movie = movieService.findMovie(movieId);
        return screenSave(whenScreened, movie);
    }

    public Screening findScreen(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보 입니다."));
    }

    public PageMovieResponseDto searchScreening(String title, LocalDateTime startTime, Pageable pageable) {
        return PageMovieResponseDto.of(screeningRepository.findScreeningStartTimeAfterAndTitle(title, startTime, pageable));
    }

    private Screening screenSave(LocalDateTime whenScreened, Movie movie) {
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(whenScreened)
                .build());
    }
}
