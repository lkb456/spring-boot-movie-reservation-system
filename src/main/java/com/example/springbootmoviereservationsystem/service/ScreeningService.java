package com.example.springbootmoviereservationsystem.service;

<<<<<<< HEAD
=======
import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
>>>>>>> feature/test
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
>>>>>>> feature/test

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;

<<<<<<< HEAD
    @Transactional
=======
>>>>>>> feature/test
    public Screening saveScreen(Long movieId, LocalDateTime whenScreened) {
        Movie movie = movieService.findMovie(movieId);
        return screenSave(whenScreened, movie);
    }

<<<<<<< HEAD
    public Screening findScreen(Long screenId) {
        return screeningRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보입니다."));
=======
    public Screening findScreen(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보 입니다."));
    }

    public PageMovieResponseDto searchScreening(String title, LocalDateTime startTime, Pageable pageable) {
        return PageMovieResponseDto.of(screeningRepository.findScreeningStartTimeAfterAndTitle(title, startTime, pageable));
>>>>>>> feature/test
    }

    private Screening screenSave(LocalDateTime whenScreened, Movie movie) {
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(whenScreened)
                .build());
    }
}
