package com.example.springbootmoviereservationsystem.controller.screening.dto;

import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;

import java.time.LocalDateTime;

public interface ScreenDtoProjection {

    MovieDtoProjection getMovie();

    LocalDateTime getWhenScreened();

    void setMovie(Movie movie);

    void setWhenScreened(LocalDateTime when);

}
