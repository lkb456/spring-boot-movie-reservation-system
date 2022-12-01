package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepository movieRepository;

    public Movie findMovie(Long movieId) {
        return find(movieId);
    }

    private Movie find(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }
}
