package com.example.springbootmoviereservationsystem.service;

import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieRequestDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.movie.dto.MovieResponsePageDto;
import com.example.springbootmoviereservationsystem.domain.money.Money;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
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

    private final MovieRepository movieRepository;

    public Long saveMovie(MovieRequestDto movieSaveRequestDto) {
        Movie savedMovie = movieRepository.save(movieSaveRequestDto.toEntity());
        return savedMovie.getId();
    }

    @Transactional
    public void updateMovie(Long movieId, MovieRequestDto movieRequestDto) {
        Movie movie = findMovie(movieId);
        movie.updateInfo(
                movieRequestDto.getTitle(),
                Money.wons(movieRequestDto.getFee()),
                movieRequestDto.getRunningTime(),
                movieRequestDto.getReleaseStatus()
        );
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    public Movie findMovie(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
    }

    public MovieResponsePageDto searchMovies(String title, ReleaseStatus status, Pageable pageable) {
        Page<MovieResponseDto> result = movieRepository.findByTitleLikeOrReleaseStatusEqualsOrderByTitle(
                title,
                status,
                pageable
        );
        return MovieResponsePageDto.of(result);
    }
}
