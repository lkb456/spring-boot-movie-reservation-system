package com.example.movie_reservation.controller.movie;

import com.example.movie_reservation.controller.movie.dto.MovieRequestDto;
import com.example.movie_reservation.controller.movie.dto.MovieResponseDto;
import com.example.movie_reservation.controller.movie.dto.MovieResponsePageDto;
import com.example.movie_reservation.service.MovieService;
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
    public ResponseEntity<Long> movieSave(@Valid @RequestBody final MovieRequestDto movieRequestDto) {
        Long savedMovieId = movieService.saveMovie(movieRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieId);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieResponseDto> movieFind(@PathVariable("id") final Long movieId) {
        MovieResponseDto movieResponseDto = MovieResponseDto.of(movieService.findMovie(movieId));
        return ResponseEntity.status(HttpStatus.OK).body(movieResponseDto);
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieResponsePageDto> movieSearch(@RequestParam(value = "title", required = false) final String title,
                                                            @PageableDefault Pageable pageable) {
        MovieResponsePageDto movieResponsePageDto = movieService.searchMovies(title, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponsePageDto);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> movieUpdate(@PathVariable("id") final Long movieId,
                                            @Valid @RequestBody final MovieRequestDto movieRequestDto) {
        movieService.updateMovie(movieId, movieRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> movieDelete(@PathVariable("id") final Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/movies/actors-add")
    public ResponseEntity<Void> addMovieActor(@RequestParam Long actorId, @RequestParam Long movieId) {
        movieService.movieActorAdd(actorId, movieId);
        return ResponseEntity.ok().build();
    }
}