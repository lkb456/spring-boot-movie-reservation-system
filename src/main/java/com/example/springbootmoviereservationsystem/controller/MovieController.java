package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.MovieSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.MovieSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieSaveResponseDto> movieSave(@RequestBody MovieSaveRequestDto movieSaveRequestDto) {
        Movie savedMovie = movieRepository.save(movieSaveRequestDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieSaveResponseDto.of(savedMovie));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieSaveResponseDto> movieFind(@PathVariable("id") Long movieId) {
        MovieSaveResponseDto movieSaveResponseDto = movieService.findMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(movieSaveResponseDto);
    }
}