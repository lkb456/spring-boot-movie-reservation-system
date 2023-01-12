package com.example.movie_reservation.controller.movie.dto;

import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MovieResponseDto {

    @JsonProperty("id")
    private final Long id; // 저장 순번

    private final String title; // 제목

    private final BigDecimal fee; // 요금

    @JsonProperty("running_time")
    private final Duration runningTime; // 상영 시간

    @JsonProperty("status")
    private final ReleaseStatus releaseStatus; // 개봉 상태

    @JsonProperty("create_at")
    private final LocalDateTime createAt; // 정보 저장 시간

    @Builder
    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.fee = movie.getFee();
        this.runningTime = movie.getRunningTime();
        this.releaseStatus = movie.getReleaseStatus();
        this.createAt = movie.getCreateAt();
    }

    public static MovieResponseDto of(Movie movie) {
        return MovieResponseDto.builder()
                .movie(movie)
                .build();
    }
}
