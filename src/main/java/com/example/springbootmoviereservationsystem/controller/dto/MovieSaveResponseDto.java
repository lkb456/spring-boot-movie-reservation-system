package com.example.springbootmoviereservationsystem.controller.dto;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MovieSaveResponseDto {

    private final Long id; // 저장 순번
    private final String title; // 제목
    private final Long fee; // 요금
    private final Duration runningTime; // 상영 시간
    private final ReleaseStatus releaseStatus; // 개봉 상태
    private final LocalDateTime createAt; // 정보 저장 시간

    public static MovieSaveResponseDto of(Movie movie) {
        return MovieSaveResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .fee(movie.getFee())
                .runningTime(movie.getRunningTime())
                .releaseStatus(movie.getReleaseStatus())
                .createAt(movie.getCreateAt())
                .build();
    }
}
