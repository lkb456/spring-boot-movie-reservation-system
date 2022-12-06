package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.request.MovieUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.Movie;
import com.example.springbootmoviereservationsystem.domain.repository.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepository movieRepository;

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }

    @Transactional
    public Long updateMovie(Long movieId, MovieUpdateRequestDto movieUpdateRequestDto) {
        Movie movie = findMovie(movieId);
        movie.update(movieUpdateRequestDto.getTitle(),
                movieUpdateRequestDto.getFee(),
                movieUpdateRequestDto.getRunningTime(),
                movieUpdateRequestDto.getReleaseStatus());
        return movieId;
    }

    public void deleteMovie(Long movieId) {
        Movie movie = findMovie(movieId);
        movieRepository.delete(movie);
    }

    public PageMovieResponseDto searchMovie(String title, ReleaseStatus status, Pageable pageable) {
        return PageMovieResponseDto.of(movieRepository
                .findByTitleLikeAndReleaseMovie(title, status, pageable));
    }
}
