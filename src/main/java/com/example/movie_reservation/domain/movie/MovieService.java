package com.example.movie_reservation.domain.movie;

import com.example.movie_reservation.domain.movie.dtos.RequestMovieDto;
import com.example.movie_reservation.domain.movie.dtos.ResponseMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Long saveMovie(RequestMovieDto requestMovieDto) {
        Movie movie = requestMovieDto.toEntity();
        return movieRepository.save(movie).getId();
    }


    public List<ResponseMovieDto> searchMovie(LocalDate firstDate, LocalDate secondDate, String keyword) {
        dateRangeCheck(firstDate, secondDate);
        List<Movie> movies = movieRepository.findByReleaseDateBetweenAndTitleStartingWith(firstDate, secondDate, keyword);
        return movies.stream()
                .map(ResponseMovieDto::of)
                .collect(Collectors.toList());
    }

    private void dateRangeCheck(LocalDate firstDate, LocalDate secondDate) {
        if (!secondDate.isAfter(firstDate)) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "LocalDate Range Exception!!");
        }
    }

    public ResponseMovieDto findMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("정보를 찾을 수 없습니다."));
        return ResponseMovieDto.of(movie);
    }
}
