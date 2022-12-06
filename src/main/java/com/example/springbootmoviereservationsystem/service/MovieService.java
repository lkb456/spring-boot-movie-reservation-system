package com.example.springbootmoviereservationsystem.service;

<<<<<<< HEAD
import com.example.springbootmoviereservationsystem.controller.dto.request.MovieUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
=======
import com.example.springbootmoviereservationsystem.controller.dto.PageMovieResponseDto;
import com.example.springbootmoviereservationsystem.controller.dto.request.MovieUpdateRequestDto;
import com.example.springbootmoviereservationsystem.domain.movie.Movie;
import com.example.springbootmoviereservationsystem.domain.movie.MovieRepository;
import com.example.springbootmoviereservationsystem.domain.movie.ReleaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
>>>>>>> feature/test
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
<<<<<<< HEAD
=======

    public void deleteMovie(Long movieId) {
        Movie movie = findMovie(movieId);
        movieRepository.delete(movie);
    }

    public PageMovieResponseDto searchMovie(String title, ReleaseStatus status, Pageable pageable) {
        return PageMovieResponseDto.of(movieRepository
                .findByTitleLikeAndReleaseMovie(title, status, pageable));
    }
>>>>>>> feature/test
}
