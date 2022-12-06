package com.example.springbootmoviereservationsystem.controller;

<<<<<<< HEAD
=======
import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
>>>>>>> feature/test
import com.example.springbootmoviereservationsystem.controller.dto.request.MovieSaveRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.request.MovieUpdateRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.response.MovieSaveResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
<<<<<<< HEAD
import com.example.springbootmoviereservationsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
=======
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import com.example.springbootmoviereservationsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
>>>>>>> feature/test
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<Long> movieSave(@RequestBody MovieSaveRequestDto movieSaveRequestDto) {
        Movie savedMovie = movieRepository.save(movieSaveRequestDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie.getId());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieSaveResponseDto> movieFind(@PathVariable("id") Long movieId) {
        MovieSaveResponseDto movieSaveResponseDto = MovieSaveResponseDto.of(movieService.findMovie(movieId));
        return ResponseEntity.status(HttpStatus.OK).body(movieSaveResponseDto);
    }

<<<<<<< HEAD
=======
    @GetMapping("/movies")
    public ResponseEntity<PageMovieResponseDto> movieSearch(@RequestParam("title") String title,
                                            @RequestParam(value = "status", required = false) ReleaseStatus status,
                                            @PageableDefault Pageable pageable) {
        PageMovieResponseDto pageMovieResponseDto = movieService.searchMovie(title, status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageMovieResponseDto);
    }

>>>>>>> feature/test
    @PutMapping("/movies/{id}")
    public ResponseEntity<Long> movieUpdate(@PathVariable("id") Long movieId,
                                            @RequestBody MovieUpdateRequestDto movieUpdateRequestDto) {
        Long updatedMovieId = movieService.updateMovie(movieId, movieUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMovieId);
    }
<<<<<<< HEAD
=======

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> movieDelete(@PathVariable("id") Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
>>>>>>> feature/test
}