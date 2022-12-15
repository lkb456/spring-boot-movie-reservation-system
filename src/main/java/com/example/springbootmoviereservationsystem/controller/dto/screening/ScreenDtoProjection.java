package com.example.springbootmoviereservationsystem.controller.dto.screening;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieDtoProjection;

import java.time.LocalDateTime;

public interface ScreenDtoProjection {

    MovieDtoProjection getMovie();
    LocalDateTime getWhenScreened();

}
