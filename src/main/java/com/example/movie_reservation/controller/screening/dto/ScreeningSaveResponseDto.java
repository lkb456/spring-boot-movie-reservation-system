package com.example.movie_reservation.controller.screening.dto;

import com.example.movie_reservation.controller.movie.dto.MovieResponseDto;
import com.example.movie_reservation.domain.movie.Movie;
import com.example.movie_reservation.domain.screening.Screening;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScreeningSaveResponseDto {

    @JsonProperty("id")
    private final Long id; // 상영 순번

    @JsonProperty("movie")
    private final MovieResponseDto movie; // 영화 정보

    @JsonProperty("when")
    private final LocalDateTime whenScreened; // 상영 시간

    @Builder
    public ScreeningSaveResponseDto(Long id, Movie movie, LocalDateTime whenScreened) {
        this.id = id;
        this.movie = MovieResponseDto.of(movie);
        this.whenScreened = whenScreened;
    }

    public static ScreeningSaveResponseDto of(Screening savedScreening) {
        return ScreeningSaveResponseDto.builder()
                .id(savedScreening.getId())
                .movie(savedScreening.getMovie())
                .whenScreened(savedScreening.getWhenScreened())
                .build();
    }
}
