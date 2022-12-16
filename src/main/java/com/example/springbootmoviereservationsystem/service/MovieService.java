package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.controller.dto.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.domain.repository.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.type.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepository movieRepository;

    @Transactional
    public void updateMovie(Long movieId, MovieRequestDto.MovieUpdateDto movieUpdateRequestDto) {
        Movie movie = findMovie(movieId);
        movie.update(movieUpdateRequestDto.getTitle(),
                movieUpdateRequestDto.getFee(),
                movieUpdateRequestDto.getRunningTime(),
                movieUpdateRequestDto.getReleaseStatus());
    }

    public void deleteMovie(Long movieId) {
        Movie movie = findMovie(movieId);
        movieRepository.delete(movie);
    }

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }

    public MovieResponseDto.PageMovieDto searchMovies(String title, ReleaseStatus status, Pageable pageable) {
        Page<MovieDtoProjection> result = movieRepository.findByTitleLikeAndReleaseMovie(title, status, pageable);
        return MovieResponseDto.PageMovieDto.of(result);
    }
}
