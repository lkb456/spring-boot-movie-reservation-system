package com.example.movie_reservation.module.main.dto;

import com.example.movie_reservation.module.actor.controller.dto.ActorResponseDto;
import com.example.movie_reservation.module.screening.domain.movie.dto.MovieResponseDto;
import com.example.movie_reservation.module.actor.domain.Actor;
import com.example.movie_reservation.module.screening.domain.movie.domain.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SearchMovieOrActorResponseDto {

    private List<MovieResponseDto> movies;
    private List<ActorResponseDto> actors;

    @Builder
    public SearchMovieOrActorResponseDto(List<MovieResponseDto> movies, List<ActorResponseDto> actors) {
        this.movies = movies;
        this.actors = actors;
    }

    public static SearchMovieOrActorResponseDto of(List<Movie> movies, List<Actor> actors) {
        return SearchMovieOrActorResponseDto.builder()
                .movies(movies.stream()
                        .map(MovieResponseDto::of)
                        .collect(Collectors.toList()))
                .actors(actors.stream()
                        .map(actor -> ActorResponseDto.of(actor))
                        .collect(Collectors.toList()))
                .build();
    }
}
