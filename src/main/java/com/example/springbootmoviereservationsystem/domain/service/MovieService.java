package com.example.springbootmoviereservationsystem.domain.service;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.controller.movie.MovieDtoProjection;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    public final MovieRepository movieRepository;

    @Transactional
    public void updateMovie(Long movieId, MovieRequestDto.MovieUpdateDto movieUpdateRequestDto) {
        Movie movie = findMovie(movieId);
        movie.updateInfo(movieUpdateRequestDto.getTitle(),
                movieUpdateRequestDto.getFee(),
                movieUpdateRequestDto.getRunningTime(),
                movieUpdateRequestDto.getReleaseStatus());
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
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
