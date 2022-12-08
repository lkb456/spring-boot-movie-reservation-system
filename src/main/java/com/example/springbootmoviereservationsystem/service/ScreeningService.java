package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.Screening;
import com.example.springbootmoviereservationsystem.domain.repository.ScreeningRepository;
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

    public MovieResponseDto.PageMovieDto searchScreening(MovieRequestDto.SearchMovieDto searchMovieRequestDto, Pageable pageable) {
        return MovieResponseDto.PageMovieDto
                .of(screeningRepository.findScreeningStartTimeAfterAndTitle(
                        searchMovieRequestDto.getTitle(),
                        searchMovieRequestDto.getStartTime(),
                        pageable,
                        MovieResponseDto.MovieDto.class));
    }

    private Screening screenSave(LocalDateTime whenScreened, Movie movie) {
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(whenScreened)
                .build());
    }
}
