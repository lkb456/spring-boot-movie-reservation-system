package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.screening.dto.PageScreenResponseDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.screening.dto.ScreeningSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.screening.Screening;
import com.example.springbootmoviereservationsystem.domain.screening.ScreeningRepository;
import com.example.springbootmoviereservationsystem.infra.policy.AmountDiscountPolicy;
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
    private final AmountDiscountPolicy discountPolicy;

    @Transactional
    public Screening saveScreen(ScreeningSaveRequestDto screeningSaveRequestDto) {
        Movie movie = movieService.findMovie(screeningSaveRequestDto.getMovieId());
        movie.changeDiscountPolicy(discountPolicy);
        return getSavedScreening(screeningSaveRequestDto, movie);
    }

    private Screening getSavedScreening(ScreeningSaveRequestDto screeningSaveRequestDto, Movie movie) {
        Screening screening = Screening.builder()
                .movie(movie)
                .whenScreened(screeningSaveRequestDto.getWhen())
                .build();
        return screeningRepository.save(screening);
    }

    public PageScreenResponseDto searchScreens(String title, LocalDateTime whenScreened, Pageable pageable) {
        Page<ScreeningSaveResponseDto> projectionPage = screeningRepository
                .findByMovieTitleStartingWithAndWhenScreenedGreaterThanEqual(title, whenScreened, pageable);
        return PageScreenResponseDto.of(projectionPage);
    }

    public Screening findScreen(Long screenId) {
        return screeningRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영정보입니다."));
    }
}
