package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreenDtoProjection;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScreeningService {

    private final MovieService movieService;
    private final ScreeningRepository screeningRepository;

    @Transactional
    public Screening saveScreen(ScreeningSaveRequestDto screeningSaveRequestDto) {
        Movie movie = movieService.findMovie(screeningSaveRequestDto.getMovieId());
        return screeningRepository.save(Screening.builder()
                .movie(movie)
                .whenScreened(screeningSaveRequestDto.getWhen())
                .build());
    }

    public ScreeningResponseDto.PageScreenResponseDto searchScreens(String title, LocalDateTime whenScreened, Pageable pageable) {
        Page<ScreenDtoProjection> projectionPage = screeningRepository.findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(title, whenScreened, pageable);
        return ScreeningResponseDto.PageScreenResponseDto.of(projectionPage);
    }

    public Screening findScreen(Long screenId) {
        return screeningRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보입니다."));
    }
}
