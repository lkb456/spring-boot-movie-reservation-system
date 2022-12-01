package com.example.springbootmoviereservationsystem.controller.dto.response;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class MovieSaveResponseDto {

    @JsonProperty("movie_id")
    private final Long id; // 저장 순번

    private final String title; // 제목

    private final Long fee; // 요금

    @JsonProperty("running_time")
    private final Duration runningTime; // 상영 시간

    @JsonProperty("status")
    private final ReleaseStatus releaseStatus; // 개봉 상태

    @JsonProperty("create_at")
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
