package com.example.movie_reservation.domain.movie.dtos;

import com.example.movie_reservation.domain.movie.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Builder
@RequiredArgsConstructor
@Getter
public class ResponseMovieDto {

    private final Long id;
    private final String title;
    private final Duration runningTime;
    private final LocalDate releaseDate;

    public static ResponseMovieDto of(Movie movie) {
        return ResponseMovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .runningTime(movie.getRunningTime())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}
