package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.SearchMovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;

    @Transactional
    public Screening saveScreen(Long movieId, LocalDateTime startTime) {
        Movie movie = movieService.findMovie(movieId);
        return screenSave(startTime, movie);
    }

    public Screening findScreen(Long screenId) {
        return screeningRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보입니다."));
    }

    public PageMovieResponseDto searchScreening(SearchMovieRequestDto searchMovieRequestDto, Pageable pageable) {
        return PageMovieResponseDto
                .of(screeningRepository.findScreeningStartTimeAfterAndTitle(
                        searchMovieRequestDto.getTitle(),
                        searchMovieRequestDto.getStartTime(),
                        pageable));
    }

    private Screening screenSave(LocalDateTime whenScreened, Movie movie) {
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(whenScreened)
                .build());
    }
}
