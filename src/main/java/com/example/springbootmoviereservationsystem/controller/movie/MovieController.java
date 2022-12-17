package com.example.springbootmoviereservationsystem.controller.movie;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.domain.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto.MovieUpdateDto;
import static com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto.MovieSaveDto;
import static com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto.PageMovieDto;

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