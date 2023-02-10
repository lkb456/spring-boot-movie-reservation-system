package com.example.movie_reservation.domain.movie;

import com.example.movie_reservation.domain.movie.dtos.RequestMovieDto;
import com.example.movie_reservation.domain.movie.dtos.ResponseMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<Long> movieSave(@Valid @RequestBody RequestMovieDto requestMovieDto) {
        Long savedId = movieService.saveMovie(requestMovieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<ResponseMovieDto>> movieSearch(@RequestParam("first")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDate,
                                                              @RequestParam("second")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate secondDate,
                                                              @RequestParam(required = false) String keyword) {
        List<ResponseMovieDto> responseMovieDtos = movieService.searchMovie(firstDate, secondDate, keyword);
        return ResponseEntity.status(HttpStatus.OK).body(responseMovieDtos);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<ResponseMovieDto> movieFind(@PathVariable("id") Long movieId) {
        ResponseMovieDto responseMovieDto = movieService.findMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(responseMovieDto);
    }
}
