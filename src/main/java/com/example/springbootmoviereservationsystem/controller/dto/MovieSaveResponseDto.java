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

    private final Long id;
    private final String title;
    private final Long fee;
    private final Duration runningTime;
    private final ReleaseStatus releaseStatus;
    private final LocalDateTime createAt;

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
