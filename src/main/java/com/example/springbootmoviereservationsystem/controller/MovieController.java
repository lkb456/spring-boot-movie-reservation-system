package com.example.springbootmoviereservationsystem.controller;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.repository.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import com.example.springbootmoviereservationsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto.MovieUpdateDto;
import static com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto.MovieSaveDto;
import static com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto.PageMovieDto;

@Validated
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieRepository movieRepository;

    @PostMapping("/movies")
    public ResponseEntity<Long> movieSave(@RequestBody MovieRequestDto.MovieSaveDto movieSaveRequestDto) {
        Movie savedMovie = movieRepository.save(movieSaveRequestDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie.getId());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieSaveDto> movieFind(@PathVariable("id") Long movieId) {
        MovieSaveDto movieSaveResponseDto = MovieSaveDto.of(movieService.findMovie(movieId));
        return ResponseEntity.status(HttpStatus.OK).body(movieSaveResponseDto);
    }

    @GetMapping("/movies")
    public ResponseEntity<PageMovieDto> movieSearch(@RequestParam("title") String title,
                                                    @RequestParam(value = "status", required = false) ReleaseStatus status,
                                                    @PageableDefault Pageable pageable) {
        PageMovieDto pageMovieResponseDto = movieService.searchMovies(title, status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageMovieResponseDto);
    }


    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> movieUpdate(@PathVariable("id") Long movieId,
                                            @RequestBody MovieUpdateDto movieUpdateRequestDto) {
        movieService.updateMovie(movieId, movieUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> movieDelete(@PathVariable("id") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}