package com.example.springbootmoviereservationsystem.controller.screening.dto;

import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;

import java.time.LocalDateTime;

public interface ScreenDtoProjection {

    MovieDtoProjection getMovie();
    LocalDateTime getWhenScreened();

}
