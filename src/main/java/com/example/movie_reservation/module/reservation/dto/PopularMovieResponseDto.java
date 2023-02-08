package com.example.movie_reservation.module.reservation.dto;

import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PopularMovieResponseDto {

    private final MovieResponseDto movie;
    private final Long reserveCount;

    @Builder
    public PopularMovieResponseDto(Movie movie, Long reserveCount) {
        this.movie = MovieResponseDto.of(movie);
        this.reserveCount = reserveCount;
    }
}
