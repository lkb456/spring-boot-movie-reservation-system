package com.example.springbootmoviereservationsystem.controller.movie;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponsePageDto;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<Long> movieSave(@Valid @RequestBody MovieRequestDto movieRequestDto) {
        Long savedMovieId = movieService.saveMovie(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieId);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieResponseDto> movieFind(@PathVariable("id") Long movieId) {
        MovieResponseDto movieResponseDto = MovieResponseDto.of(movieService.findMovie(movieId));
        return ResponseEntity.status(HttpStatus.OK).body(movieResponseDto);
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieResponsePageDto> movieSearch(@RequestParam("title") String title,
                                                            @RequestParam(value = "status", required = false) ReleaseStatus status,
                                                            @PageableDefault Pageable pageable) {
        MovieResponsePageDto movieResponsePageDto = movieService.searchMovies(title, status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponsePageDto);
    }


    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> movieUpdate(@PathVariable("id") Long movieId,
                                            @Valid @RequestBody MovieRequestDto movieRequestDto) {
        movieService.updateMovie(movieId, movieRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> movieDelete(@PathVariable("id") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}