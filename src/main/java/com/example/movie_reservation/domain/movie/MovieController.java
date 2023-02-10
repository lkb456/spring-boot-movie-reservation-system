package com.example.movie_reservation.domain.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    @PostMapping("/movies")
    public ResponseEntity<Long> movieSave(@Valid @RequestBody RequestMovieDto requestMovieDto) {
        Movie movie = requestMovieDto.toEntity();
        Long id = movieRepository.save(movie).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<ResponseMovieDto>> movieSearch(@RequestParam("first")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstDate,
                                                              @RequestParam("second")
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate secondDate,
                                                              @RequestParam(required = false) String title) {
        List<Movie> list = movieRepository.findByReleaseDateBetweenAndTitleStartingWith(firstDate, secondDate, title);
        return ResponseEntity.status(HttpStatus.OK).body(list.stream().map(ResponseMovieDto::of).collect(Collectors.toList()));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<ResponseMovieDto> movieFind(@PathVariable("id") Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화정보입니다."));
        ResponseMovieDto of = ResponseMovieDto.of(movie);
        return ResponseEntity.status(HttpStatus.OK).body(of);
    }
}
